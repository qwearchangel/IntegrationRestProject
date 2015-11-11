/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.controllers;

import com.filipferm.rest.ejb.EJBCollection;
import com.filipferm.rest.exeptions.DataNotFoundException;
import com.filipferm.rest.model.Customer;
import com.filipferm.rest.modelWrappers.GenericLinkWrapper;
import com.filipferm.rest.modelWrappers.GenericLinkWrapperFactory;
import com.filipferm.utils.Link;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Filip
 */
@Path("customer")
public class CustomerController {

    @EJB
    EJBCollection ec;

    @EJB
    GenericLinkWrapperFactory<Customer> glwf;

    public CustomerController() {
    }

    public CustomerController(EJBCollection ec, GenericLinkWrapperFactory<Customer> glwf) {
        this.ec = ec;
        this.glwf = glwf;
    }

    @GET
    @Produces("application/json")
    public Response getAllCustomers(@Context UriInfo uriInfo, Request request) {
        Customer customer = new Customer();
        int hashValue = 0;
        List<GenericLinkWrapper> wrappedCustomer = glwf.getAll(customer);
        for (GenericLinkWrapper<Customer> wc : wrappedCustomer) {
            List<Link> links = new ArrayList<>();
            String uri = uriInfo.getBaseUriBuilder().
                    path(CustomerController.class).
                    path(Integer.toString(wc.getEntity().getId())).
                    build().toString();
            links.add(new Link(uri, "self"));
            uri = uriInfo.getBaseUriBuilder().
                    path(CustomerController.class).
                    path(Integer.toString(wc.getEntity().getId())).path("order").
                    build().toString();
            links.add(new Link(uri, "products"));
            wc.setLink(links);
            hashValue += wc.getEntity().hashCode();
        }
        
        CacheControl cc = new CacheControl();
        cc.setMaxAge(86400);
        cc.setPrivate(true);
        
        EntityTag etag = new EntityTag(Integer.toString(hashValue));
        
        Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
        
        if(builder == null) {
            builder = Response.ok(wrappedCustomer);
            builder.tag(etag);
        }
        builder.cacheControl(cc);
        return builder.build();
    }

    @GET
    @Produces("application/json")
    @Path("{customerId}")
    public Response getCustomer(@Context UriInfo uriInfo,
            @PathParam("customerId") int id) {
         if (ec.getCustomerManager().getObjById(id) == null) {
            throw new DataNotFoundException("Customer with id " + id + " not found");
        }
        List<Customer> customers = new ArrayList<>();
        customers.add(ec.getCustomerManager().getObjById(id));
        List<GenericLinkWrapper> customer = glwf.getById(customers);
        for (GenericLinkWrapper<Customer> wc : customer) {
            List<Link> links = new ArrayList<>();
            String uri = uriInfo.getBaseUriBuilder().
                    path(CustomerController.class).
                    path(Integer.toString(wc.getEntity().getId())).
                    build().toString();
            links.add(new Link(uri, "self"));
            wc.setLink(links);
        }
        return Response.status(Status.OK).entity(customer).build();
    }

    @POST
    @Produces("application/json")
    public Response addCustomer(@FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        ec.getCustomerManager().addObj(customer);
        return Response.status(Status.CREATED).entity(customer).build();
    }

    @PUT
    @Produces("application/json")
    @Path("{id}")
    public Response editCustomer(@PathParam("id") int id,
            @FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        ec.getCustomerManager().addObj(customer);
        return Response.status(Status.ACCEPTED).entity(customer).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer customer = new Customer();
        customer.setId(id);
        ec.getCustomerManager().deleteObj(customer);
        return Response.status(Status.ACCEPTED).build();
    }
    
    @Path("{customerId}/order")
    public OrderController getOrder() {
        return new OrderController(ec);
    }
    
}
