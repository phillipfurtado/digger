package com.github.phillipfurtado.digger.handle;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.phillipfurtado.digger.exception.DiggerException;

@Provider
public class ExceptionHandler implements ExceptionMapper<DiggerException> {

    @Context
    private HttpServletResponse response;

    @Override
    public Response toResponse(DiggerException exception) {
        ResponseBuilder resp = Response.status(Status.BAD_REQUEST).entity(exception).type(MediaType.APPLICATION_JSON);
        for (String headerName : response.getHeaderNames()) {
            resp.header(headerName, response.getHeader(headerName));
        }
        return resp.build();
    }

}
