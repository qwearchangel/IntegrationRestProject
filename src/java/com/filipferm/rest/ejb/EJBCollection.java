/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Filip
 */
@Stateless
public class EJBCollection {
    
    @EJB
    CustomerManager cm;
    @EJB
    CategoryManager cam;
    @EJB
    OrderManager om;
    @EJB
    OrderitemsManager oim;
    @EJB
    ProductManager pm;

    public CustomerManager getCustomerManager() {
        return cm;
    }

    public CategoryManager getCategoryManager() {
        return cam;
    }

    public OrderManager getOrderManager() {
        return om;
    }

    public OrderitemsManager getOrderitemsManager() {
        return oim;
    }

    public ProductManager getProductManager() {
        return pm;
    }
}
