package br.com.compass.programadebolsas.projetoweb.exception;

import br.com.compass.programadebolsas.projetoweb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class idNaoEncontradoMapper implements ExceptionMapper<idNaoEncontradoException> {
    @Override
    public Response toResponse(idNaoEncontradoException e) {
        ErroMessage mensagem = new ErroMessage(e.getMessage(),Response.Status.NOT_FOUND.getStatusCode(), "http://localhost:8080/ProjetoIndividualWeb2");
        return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
    }
}


