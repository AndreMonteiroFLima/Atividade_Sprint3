package br.com.compass.programadebolsas.projetoweb.exception;

import br.com.compass.programadebolsas.projetoweb.util.UriUtils;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.net.URI;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Context
    private ServletContext servletContext;
    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException e) {
        URI contextPath= UriUtils.getFullServletContextPath(servletContext,uriInfo);
        UriBuilder builder = UriBuilder.fromUri(contextPath).path("Erro404.jsp");
        return Response.seeOther(builder.build()).build();
    }
}

