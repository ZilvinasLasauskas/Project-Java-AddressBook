/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import lt.bit.data.Person;
import lt.bit.db.DB;

/**
 *
 * @author zills
 */
@Path("/person")
public class PersonREST {

    @Context
    private HttpServletRequest request;

    @GET
    public List<Person> list() {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.getAll(em);
    }

    @POST
    public Person add(Person p) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.add(em, p);
    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.delete(em, id);
    }

    @GET
    @Path("{id}")
    public Person getPerson(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.getById(em, id);
    }
    
    @PUT
    @Path("{id}")
    public Person updatePerson(@PathParam("id") Integer id, Person p) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        p.setId(id);
        return DB.update(em, p);
    }
}
