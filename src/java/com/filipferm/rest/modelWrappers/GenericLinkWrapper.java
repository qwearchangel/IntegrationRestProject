/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.modelWrappers;

import com.filipferm.utils.Link;
import java.util.List;

/**
 *
 * @author Filip
 */
public class GenericLinkWrapper<T> {
    
    private T entity;
    private List<Link> link;

    public GenericLinkWrapper() {
    }

    public GenericLinkWrapper(T entity, List<Link> link) {
        this.entity = entity;
        this.link = link;
    }

//    public GenericLinkWrapper(T Entity, Link link) {
//        this.entity = Entity;
//        this.link = link;
//    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T t) {
        this.entity = t;
    }

//    public Link getLink() {
//        return link;
//    }
//
//    public void setLink(Link link) {
//        this.link = link;
//    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }
    
    
}
