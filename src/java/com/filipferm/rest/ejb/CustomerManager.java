/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.ejb;

import com.filipferm.rest.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class CustomerManager {

    @PersistenceContext(name = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<Customer> getAllObj() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public Customer getObjById(int id) {
        return em.find(Customer.class, id);
    }

    public void deleteObj(Customer obj) {
        em.remove(getObjById(obj.getId()));
    }

    public void addObj(Customer obj) {
        em.merge(obj);
    }

}
