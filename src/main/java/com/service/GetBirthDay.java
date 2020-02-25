package com.service;

import com.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/birthday")
public class GetBirthDay {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/birthday/getBithDay")
    public String getBirthDay(Person person){
        return "ok";
    }
}
