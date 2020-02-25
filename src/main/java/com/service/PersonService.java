package com.service;

import com.dao.DaoPerson;
import com.model.Person;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.util.List;


@Path("/personService")
public class PersonService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person>getAllPersons(){
        return (List<Person>) DaoPerson.connect();
    }

    @GET
    @Path("/{name}")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_ATOM_XML})
    public Person getPerson(@PathParam("name")String name){
        return  DaoPerson.getName(getPerson(name));
    }

}
