package com.healthSystemAPI.resources.Exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    private static final Logger w1953568LOGGER = Logger.getLogger(InternalErrorExceptionMapper.class.getName());
        
    @Override
    public Response toResponse(BadRequestException exception) {
        w1953568LOGGER.log(Level.SEVERE, exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
    
    
}
