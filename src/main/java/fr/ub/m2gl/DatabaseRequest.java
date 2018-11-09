package fr.ub.m2gl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

class DatabaseRequest {

    private MongoCollection<Document> collection;

    DatabaseRequest() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("myBase");
        this.collection = db.getCollection("myCollection");
    }

    String addUser(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);
            Document doc = Document.parse(jsonString);
            collection.insertOne(doc);
            return "\"User " + user.getFirstname() + " " + user.getLastname() + " added successfully.\"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "\"Adding new user failed\"";
    }

    String getAllUsers() {
        StringBuilder str = new StringBuilder();
        FindIterable<Document> cursor = collection.find();
        for (Document d : cursor)
            str.append(d.toJson()).append("\n");
        return str.toString();
    }

    String getUser(String id) {
        Document doc = collection.find(eq("id", id)).first();
        return doc != null ? doc.toJson() : "\"User not found\"";
    }

    String deleteUser(String id) {
        if (collection.deleteOne(eq("id", id)).getDeletedCount() == 1)
            return "\"User with id = " + id + "} deleted\"";
        return "\"User not found\"";
    }

    String updateUser(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);
            Document doc = Document.parse(jsonString);
            collection.updateOne(eq("id", user.getId()), new Document("$set", doc));
            return "\"User " + user.getFirstname() + " updated successfully\"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "\"Updating failed\"";
    }

}
