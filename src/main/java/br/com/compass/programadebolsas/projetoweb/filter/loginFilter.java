/*
package br.com.compass.programadebolsas.projetoweb.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;



@Provider
@PreMatching
public class loginFilter implements ContainerRequestFilter {
    @Context
    private HttpServletRequest httpServletRequest;
    @Context
    private HttpServletResponse httpServletResponse;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        HttpSession session = httpServletRequest.getSession();
        boolean usuarionNaoLogado = (session.getAttribute("usuarioLogado")==null);
        boolean protectedAction = !(containerRequestContext.getUriInfo().getRequestUri().getPath().equals("/ProjetoIndividualWeb2/webapp/login/") || containerRequestContext.getUriInfo().getRequestUri().getPath().equals("/ProjetoIndividualWeb2/webapp/login/mostraLogin") || containerRequestContext.getUriInfo().getRequestUri().getPath().equals("/ProjetoIndividualWeb2/webapp/cadastro/cadastroNovoUsuario")
                || containerRequestContext.getUriInfo().getRequestUri().getPath().equals("/ProjetoIndividualWeb2/webapp/cadastroView/mostraUsuario")
                || containerRequestContext.getUriInfo().getRequestUri().getPath().equals("/ProjetoIndividualWeb2/webapp/cadastroView/cadastroNovoUsuarioView"));

        if(protectedAction && usuarionNaoLogado){
            httpServletResponse.sendRedirect("http://localhost:8080/ProjetoIndividualWeb2/webapp/login/mostraLogin");
            return;
        }
    }
}
*/
