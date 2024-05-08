
package com.healthSystemAPI.resources.Exception;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MethodNotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {
    private static final Logger w1953568LOGGER = Logger.getLogger(MethodNotAllowedExceptionMapper.class.getName());
    @Override
    public Response toResponse(NotAllowedException exception) {
        String errorMessage = "The HTTP method used is not supported for this endpoint, Please correct the URl";
        w1953568LOGGER.log(Level.WARNING, exception.getMessage());
        return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                       .entity(errorMessage)
                       .type(MediaType.TEXT_PLAIN)
                       .build();
    }
    
}
