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
import lt.bit.data.Address;
import lt.bit.data.Person;
import lt.bit.db.DB;

/**
 *
 * @author zills
 */
@Path("person/{personId}/address")

public class AddressREST {

    @Context
    private HttpServletRequest request;

    @GET
    public List<Address> address(@PathParam("personId") Integer personId) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.getPersonAddresses(em, personId);
    }
    
    @POST
    public Address addAddress (@PathParam("personId") Integer personId, Address a) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.addAddress(em, personId, a);
    }
 
    @DELETE
    @Path("{id}")
    public void deleteAddress(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.deleteAddress(em, id);
    }

    @GET
    @Path("{id}")
    public Address getAddressById(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return DB.getAddressById(em, id);
    }
    
    @PUT
    @Path("{id}")
    public Address updateAddress(@PathParam("id") Integer id, Address a) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        a.setId(id);
        return DB.updateAddress(em, a);
    }
}
