/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.modelWrappers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Filip
 */
@Stateless
public class GenericLinkWrapperFactory<T> {

    @PersistenceContext(unitName = "IntegrationRest_SoapPU")
    EntityManager em;

    public List<GenericLinkWrapper> getAll(T t) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(t.getClass()));
        List<T> entitys = em.createQuery(cq).getResultList();
        List<GenericLinkWrapper> wrappedEntitys = new ArrayList<>();
        for (Object b : entitys) {
            wrappedEntitys.add(new GenericLinkWrapper(b, null));
        }
        return wrappedEntitys;
    }

    public List<GenericLinkWrapper> getById(List<T> ts) {
        List<GenericLinkWrapper> wrappedEntitysById = new ArrayList<>();
        for (Object b : ts) {
            wrappedEntitysById.add(new GenericLinkWrapper((T)b, null));
        }
        return wrappedEntitysById;
    }

}
