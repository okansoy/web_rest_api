package fr.ub.m2gl;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class ContactManager extends ResourceConfig {

    public ContactManager() {
        packages("fr.ub.m2gl");
        property(ServerProperties.TRACING, "ALL");
    }


}