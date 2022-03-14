package br.com.compass.programadebolsas.projetoweb.exception;

import br.com.compass.programadebolsas.projetoweb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CampoNuloExceptionMapper implements ExceptionMapper<CampoNuloException> {

    @Override
    public Response toResponse(CampoNuloException e) {
        ErroMessage mensagem = new ErroMessage(e.getMessage(),Response.Status.NOT_ACCEPTABLE.getStatusCode(),"http://localhost:8080/ProjetoIndividualWeb2");
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(mensagem).build();
    }
}
