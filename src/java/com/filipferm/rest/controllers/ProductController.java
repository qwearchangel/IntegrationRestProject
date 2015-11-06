/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.controllers;

import com.filipferm.rest.ejb.EJBCollection;
import com.filipferm.rest.model.Product;
import com.filipferm.rest.modelWrappers.GenericLinkWrapperFactory;
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

/**
 *
 * @author Filip
 */
@Path("product")
public class ProductController {

    @EJB
    EJBCollection ec;
    @EJB
    GenericLinkWrapperFactory<Product> glwf;

    public ProductController() {
    }

    public ProductController(EJBCollection ec) {
        this.ec = ec;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductFromCategory(@PathParam("categoryId") int catId) {
        List<Product> products = ec.getProductManager().getAllObj();
        if (catId == 0) {
            return products;
        }
        List<Product> categoryProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().getId().equals(catId)) {
                categoryProduct.add(product);
            }
        }
        return categoryProduct;
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Product getProduct(@PathParam("id") int id) {
        return ec.getProductManager().getObjById(id);
    }

    @POST
    @Produces("appliction/json")
    public Product addProduct(@PathParam("categoryId") int catId,
            @FormParam("productName") String productName,
            @FormParam("price") double price,
            @FormParam("size") int size,
            @FormParam("description") String desc) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setProductSize(size);
        product.setDescription(desc);
        ec.getProductManager().addObj(product, catId);
        return product;
    }

    @PUT
    @Produces("application/json")
    @Path("{id}")
    public Product editProduct(@PathParam("categoryId") int catId,
            @PathParam("id") int id,
            @FormParam("productName") String productName,
            @FormParam("price") double price,
            @FormParam("size") int size,
            @FormParam("description") String desc) {
        Product product = new Product();
        product.setId(id);
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setProductSize(size);
        product.setDescription(desc);
        ec.getProductManager().addObj(product, catId);
        return product;
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") int id) {
        Product product = new Product();
        product.setId(id);
        ec.getProductManager().deleteObj(product);
    }
}
