
package com.healthSystemAPI.resources.Exception;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    private static final Logger w1953568LOGGER = Logger.getLogger(InternalErrorExceptionMapper.class.getName());
    @Override
    public Response toResponse(UserNotFoundException exception) {
        w1953568LOGGER.log(Level.INFO, exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(exception.getMessage())
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}




