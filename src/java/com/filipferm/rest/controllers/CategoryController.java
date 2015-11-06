/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.controllers;

import com.filipferm.rest.ejb.CategoryManager;
import com.filipferm.rest.ejb.EJBCollection;
import com.filipferm.rest.model.Category;
import com.filipferm.rest.modelWrappers.GenericLinkWrapper;
import com.filipferm.rest.modelWrappers.GenericLinkWrapperFactory;
import com.filipferm.utils.Link;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Filip
 */
@Path("category")
public class CategoryController {

    @EJB
    EJBCollection ec;

    @EJB
    GenericLinkWrapperFactory<Category> glwf;

    public CategoryController() {
    }

    public CategoryController(EJBCollection ec, GenericLinkWrapperFactory<Category> glwf) {
        this.ec = ec;
        this.glwf = glwf;
    }

    @GET
    @Produces("application/json")
    public Response getAllCategorys(@Context UriInfo uriInfo) {
        Category category = new Category();
        List<GenericLinkWrapper> wrappedCategory = glwf.getAll(category);
        for (GenericLinkWrapper<Category> wc : wrappedCategory) {
            List<Link> links = new ArrayList<>();
            String uri = uriInfo.getBaseUriBuilder().
                    path(CategoryController.class).
                    path(Integer.toString(wc.getEntity().getId())).
                    build().toString();
            links.add(new Link(uri, "self"));
            uri = uriInfo.getBaseUriBuilder().
                    path(CategoryController.class).
                    path(Integer.toString(wc.getEntity().getId())).path("product").
                    build().toString();
            links.add(new Link(uri, "products"));
            wc.setLink(links);
        }
        return Response.status(Status.OK).entity(wrappedCategory).build();
    }

    @GET
    @Produces("application/json")
    @Path("{categoryId}")
    public Response getOneCategory(@Context UriInfo uriInfo,
            @PathParam("categoryId") int id) {
        List<Category> categorys = new ArrayList<>();
        categorys.add(ec.getCategoryManager().getObjById(id));
        List<GenericLinkWrapper> category = glwf.getById(categorys);
        for (GenericLinkWrapper<Category> wc : category) {
            List<Link> links = new ArrayList<>();
            String uri = uriInfo.getBaseUriBuilder().
                    path(CategoryController.class).
                    path(Integer.toString(wc.getEntity().getId())).
                    build().toString();
            links.add(new Link(uri, "self"));
            uri = uriInfo.getBaseUriBuilder().
                    path(CategoryController.class).
                    path(Integer.toString(wc.getEntity().getId())).path("product").
                    build().toString();
            links.add(new Link(uri, "products"));
            wc.setLink(links);
        }
        return Response.status(Status.OK).entity(category).build();
    }

    @POST
    @Produces("applicaiton/json")
    public Response addCategory(
            @FormParam("categoryName")
            @NotNull(message = "category name cannot be null!") String categoryName,
            @FormParam("descrption") String desc) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(desc);
        ec.getCategoryManager().addObj(category);
        return Response.status(Status.CREATED).entity(category).build();
    }

    @PUT
    @Produces("application/json")
    @Path("{categoryId}")
    public Response editCategory(
            @PathParam("categoryId") int id,
            @FormParam("categoryName") String categoryName,
            @FormParam("descrption") String desc) {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        category.setDescription(desc);
        ec.getCategoryManager().addObj(category);
        return Response.status(Status.ACCEPTED).entity(category).build();
    }

    @DELETE
    @Path("{categoryId}")
    public Response deleteCategory(@PathParam("id") int id) {
        Category category = new Category();
        category.setId(id);
        ec.getCategoryManager().deleteObj(category);
        return Response.status(Status.ACCEPTED).build();
    }

    @Path("{categoryId}/product")
    public ProductController getProduct() {
        return new ProductController(ec);
    }
}
