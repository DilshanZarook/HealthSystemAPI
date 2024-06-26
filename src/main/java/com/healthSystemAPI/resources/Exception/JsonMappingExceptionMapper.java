
package com.healthSystemAPI.resources.Exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException exception) {
        String errorMessage = "Invalid JSON input: " + exception.getOriginalMessage();
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(errorMessage)
                       .type("text/plain")
                       .build();
    }
}

