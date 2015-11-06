/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.ejb;

import com.filipferm.rest.model.Category;
import com.filipferm.rest.model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class ProductManager {
    @PersistenceContext(name = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<Product> getAllObj() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Product getObjById(int id) {
        return em.find(Product.class, id);
    }

    public void deleteObj(Product obj) {
        em.remove(getObjById(obj.getId()));
    }

    public void addObj(Product obj, int cid) {
        obj.setCategory(em.find(Category.class,cid));
        em.merge(obj);
    }
}
