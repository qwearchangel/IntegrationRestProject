/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.ejb;

import com.filipferm.rest.model.Category;
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
public class CategoryManager{
    @PersistenceContext(name = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<Category> getAllObj() {
        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    public Category getObjById(int id) {
        return em.find(Category.class, id);
    }

    public void deleteObj(Category obj) {
        em.remove(getObjById(obj.getId()));
    }

    public void addObj(Category obj) {
        em.merge(obj);
    }
}
