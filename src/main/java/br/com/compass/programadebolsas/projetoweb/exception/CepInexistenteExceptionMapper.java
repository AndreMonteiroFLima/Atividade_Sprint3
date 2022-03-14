package br.com.compass.programadebolsas.projetoweb.exception;

import br.com.compass.programadebolsas.projetoweb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CepInexistenteExceptionMapper implements ExceptionMapper<CepInexistenteException> {

    @Override
    public Response toResponse(CepInexistenteException e) {
        ErroMessage mensagem = new ErroMessage("CEP inválido por favor tente um valido", Response.Status.NOT_FOUND.getStatusCode(),"http://localhost:8080/ProjetoIndividualWeb2");
        return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
    }
}
