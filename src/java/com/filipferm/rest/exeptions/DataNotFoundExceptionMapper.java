/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.exeptions;

import com.filipferm.rest.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Filip
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

    @Override
    public Response toResponse(DataNotFoundException ex) {
       ErrorMessage message = new ErrorMessage(ex.getMessage(),404,"http://IntegrationRest/api/documentation");
        return Response.status(Response.Status.NOT_FOUND).entity(message).build();
    }
    
}
