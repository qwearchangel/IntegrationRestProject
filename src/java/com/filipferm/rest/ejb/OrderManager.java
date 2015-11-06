/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.ejb;

import com.filipferm.rest.model.Customer;
import com.filipferm.rest.model.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class OrderManager{
    @PersistenceContext(name = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<Orders> getAllObj() {
        return em.createNamedQuery("Orders.findAll",Orders.class).getResultList();
    }

    public Orders getObjById(int id) {
        return em.find(Orders.class, id);
    }

    public void deleteObj(Orders obj) {
        em.remove(getObjById(obj.getId()));
    }

    public void addObj(Orders obj, int cid) {
        obj.setCustomer(em.find(Customer.class, cid)); 
        em.merge(obj);
    }
}
