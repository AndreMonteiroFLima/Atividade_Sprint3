package br.com.compass.programadebolsas.projetoweb.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {
    @Context
    private HttpServletRequest httpServletRequest;
    @Context
    private HttpServletResponse httpServletResponse;
    private final SecretKey CHAVE =
            Keys.hmacShaKeyFor("R6xG$fed=9vtkb8XR2JyPOD43+DHG!qPJ$X9#RmuVVgd5vOa!nwxu68xB%Vp9ry6"
                    .getBytes(StandardCharsets.UTF_8));

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        boolean usuarionNaoLogado = (session.getAttribute("usuarioLogado")==null);
        if(!usuarionNaoLogado) {
            try {

                String token = session.getAttribute("jwtToken").toString();
                final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
                requestContext.setSecurityContext(new SecurityContext() {

                    @Override
                    public Principal getUserPrincipal() {
                        return () -> Jwts.parserBuilder().setSigningKey(CHAVE).build().parseClaimsJws(token).getBody().getSubject();
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        return currentSecurityContext.isSecure();
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return token;
                    }
                });

                Jwts.parserBuilder().setSigningKey(CHAVE).build().parseClaimsJws(token);
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }else
            httpServletResponse.sendRedirect("http://localhost:8080/ProjetoIndividualWeb2/webapp/login/mostraLogin");
    }


}
