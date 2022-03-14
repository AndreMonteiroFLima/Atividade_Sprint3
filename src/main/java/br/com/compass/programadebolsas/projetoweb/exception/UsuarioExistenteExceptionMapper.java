package br.com.compass.programadebolsas.projetoweb.exception;

import br.com.compass.programadebolsas.projetoweb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsuarioExistenteExceptionMapper implements ExceptionMapper<UsuarioExistenteException> {

    @Override
    public Response toResponse(UsuarioExistenteException e) {
        ErroMessage mensagem = new ErroMessage("Esse Usuario Ja existe", Response.Status.BAD_REQUEST.getStatusCode(),"http://localhost:8080/ProjetoIndividualWeb2");
        return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
    }
    
}
