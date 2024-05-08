
package com.healthSystemAPI.resources.Exception;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    private static final Logger w1953568LOGGER = Logger.getLogger(InternalErrorExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(NotFoundException exception) {
        w1953568LOGGER.log(Level.SEVERE, exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(exception.getMessage())
                       .build();
    }
}



