
package com.healthSystemAPI.resources.Exception;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalErrorExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger w1953568LOGGER = Logger.getLogger(InternalErrorExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        w1953568LOGGER.log(Level.SEVERE, exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(exception.getMessage())
                       .type(MediaType.TEXT_PLAIN)
                       .build();
    }
}

