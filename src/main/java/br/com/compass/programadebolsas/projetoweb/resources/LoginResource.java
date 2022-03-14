package br.com.compass.programadebolsas.projetoweb.resources;

import br.com.compass.programadebolsas.projetoweb.services.LoginService;
import br.com.compass.programadebolsas.projetoweb.util.HttpUtils;
import br.com.compass.programadebolsas.projetoweb.util.UriUtils;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import javax.crypto.SecretKey;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Path("login")
public class LoginResource {
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;

    private LoginService loginService= new LoginService();
    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "R6xG$fed=9vtkb8XR2JyPOD43+DHG!qPJ$X9#RmuVVgd5vOa!nwxu68xB%Vp9ry6"
                    .getBytes(StandardCharsets.UTF_8));

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response geraChave(@FormParam("username") String username,
                              @FormParam("password") String password)
    {
        try{
            return loginService.verificaUsuario(username,password,httpRequest);
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Path("/mostraLogin")
    @GET
    public Response mostraLogin(@Context ServletContext servletContext, @Context UriInfo uriInfo){
        URI contextPath= UriUtils.getFullServletContextPath(servletContext,uriInfo);
        UriBuilder builder = UriBuilder.fromUri(contextPath).path("LoginForm.jsp");
        return Response.seeOther(builder.build()).build();
    }

    @Path("/desloga")
    @GET
    public Response desloga(){
        HttpSession session= new HttpUtils().getSession(httpRequest);
        session.invalidate();
        try {
            return Response.seeOther(new URI("http://localhost:8080/ProjetoIndividualWeb2/webapp/login/mostraLogin")).build();
        } catch (URISyntaxException e) {
           throw new RuntimeException();
        }
    }

}

