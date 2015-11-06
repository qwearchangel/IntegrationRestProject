/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.controllers;

import com.filipferm.rest.ejb.EJBCollection;
import com.filipferm.rest.model.Orders;
import com.filipferm.rest.modelWrappers.GenericLinkWrapperFactory;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author Filip
 */
@Path("order")
public class OrderController {

    @EJB
    EJBCollection ec;
    @EJB
    GenericLinkWrapperFactory<Orders> glwf;

    public OrderController() {
    }

    public OrderController(EJBCollection ec) {
        this.ec = ec;
    }

    @GET
    @Produces("application/json")
    public List<Orders> getAllCustomersWithOrder(@PathParam("customerId") int custId) {
        List<Orders> orders = ec.getOrderManager().getAllObj();
        if (custId == 0) {
            return orders;
        }
        List<Orders> customerOrders = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getCustomer().getId().equals(custId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Orders getCustomer(@PathParam("id") int id) {
        return ec.getOrderManager().getObjById(id);
    }

    @POST
    @Produces("application/json")
    public Orders addCustomer(@PathParam("customerId") int custId,
            @FormParam("message") String message) {
        Orders order = new Orders();
        order.setMessage(message);
        order.setOrderDate(new Date());
        ec.getOrderManager().addObj(order, custId);
        return order;
    }

    @PUT
    @Produces("application/json")
    @Path("{id}")
    public Orders editCustomer(@PathParam("id") int id,
            @PathParam("customerId") int custID,
            @FormParam("message") String message) {
        Orders order = new Orders();
        order.setId(id);
        order.setMessage(message);
        order.setOrderDate(new Date());
        ec.getOrderManager().addObj(order, custID);
        return order;
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        Orders order = new Orders();
        order.setId(id);
        ec.getOrderManager().deleteObj(order);
    }
    
    @Path("{orderId}/orderitems")
    public OrderitemsController getOrderitemes() {
        return new OrderitemsController(ec);
    }
}
