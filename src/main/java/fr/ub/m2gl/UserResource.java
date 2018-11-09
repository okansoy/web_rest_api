package fr.ub.m2gl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

    private DatabaseRequest dtb;

    public UserResource() {
        this.dtb = new DatabaseRequest();
    }

    //curl --header "Content-Type: application/json" --request POST --data "{\"firstname\": \"Mr\",\"lastname\": \"Reveillere\"}" http://localhost:8080/user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String post(User user) {
        return dtb.addUser(user);
    }

    //curl --header "Content-Type: application/json" --request GET http://localhost:8080/user/all
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllUsers() {
        return dtb.getAllUsers();
    }

    //curl --header "Content-Type: application/json" --request GET http://localhost:8080/user/{id}
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") String id) {
        return dtb.getUser(id);
    }

    //curl --header "Content-Type: application/json" --request DELETE http://localhost:8080/user/{id}
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") String id) {
        return dtb.deleteUser(id);
    }

    //curl --header "Content-Type: application/json" --request PUT --data "{\"firstname\": \"Monsieur\",\"lastname\": \"R\"}" http://localhost:8080/user/47ae8daf-e74c-47ab-a5e2-11d3b95719bc
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("id") String id, User user) {
        user.setId(id);
        return dtb.updateUser(user);
    }

}
