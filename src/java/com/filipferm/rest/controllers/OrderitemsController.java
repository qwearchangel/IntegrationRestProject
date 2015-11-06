/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.controllers;

import com.filipferm.rest.ejb.EJBCollection;
import com.filipferm.rest.exeptions.DataNotFoundException;
import com.filipferm.rest.model.Orderitems;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Filip
 */
public class OrderitemsController {

    @EJB
    EJBCollection ec;

    public OrderitemsController() {
    }

    public OrderitemsController(EJBCollection ec) {
        this.ec = ec;
    }
    
    @GET
    @Produces("application/json")
    public List<Orderitems> getAllOrderitems(@PathParam("{orderId}") int orderId) {
        List<Orderitems> orderitems = ec.getOrderitemsManager().getAllObj();
        if(orderId == 0) {
            return null;
        }
        List<Orderitems> orderOrderItm = new ArrayList<>();
        for(Orderitems oi : orderitems) {
            if(oi.getOrder().getId().equals(orderId)) {
                orderOrderItm.add(oi);
            }
        }
        return orderOrderItm;
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        Orderitems orderitem = ec.getOrderitemsManager().getObjById(id);
        return Response.status(Status.OK).entity(orderitem).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderitem(@PathParam("oid") int oid,
            @FormParam("productId") int pid,
            @FormParam("quantity") int quantity) {
        Orderitems orderitem = new Orderitems();
        orderitem.setQuantity(quantity);
        ec.getOrderitemsManager().addObj(orderitem, oid, pid);
        return Response.status(Status.OK).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response editOrderitem(@PathParam("id") int id,
            @PathParam("oid") int oid,
            @FormParam("productId") int pid,
            @FormParam("quantity") int quantity) {
        Orderitems orderitem = new Orderitems();
        orderitem.setId(id);
        orderitem.setQuantity(quantity);
        ec.getOrderitemsManager().addObj(orderitem, oid, pid);
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrderitem(@PathParam("id") int id) {
        Orderitems orderitem = new Orderitems();
        orderitem.setId(id);
        ec.getOrderitemsManager().deleteObj(orderitem);
        return Response.status(Status.OK).build();
    }

}
