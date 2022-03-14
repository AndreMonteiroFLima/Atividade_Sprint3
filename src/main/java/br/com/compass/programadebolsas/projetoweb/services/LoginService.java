package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.UsuarioDAO;
import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import br.com.compass.programadebolsas.projetoweb.util.HttpUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import javax.crypto.SecretKey;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LoginService {
    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "R6xG$fed=9vtkb8XR2JyPOD43+DHG!qPJ$X9#RmuVVgd5vOa!nwxu68xB%Vp9ry6"
                    .getBytes(StandardCharsets.UTF_8));
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    @Context HttpServletResponse httpResponse;

    public Response verificaUsuario(String username, String password, HttpServletRequest httpServletRequest){
        Usuario usuario = verificaUsuarioNull(username,password);
        if(usuario!=null){

            String jwtToken = Jwts.builder()
                    .setSubject(usuario.getUsername())
                    .setIssuer("localhost:8080/webapp/")
                    .setIssuedAt(new Date())
                    .setExpiration(
                            Date.from(LocalDateTime.now().plusMinutes(15L)
                                      .atZone(ZoneId.systemDefault())
                                      .toInstant()))
                    .signWith(CHAVE, SignatureAlgorithm.HS512)
                    .compact();

            HttpSession session = new HttpUtils().getSession(httpServletRequest);
            session.setAttribute("usuarioLogado",usuario.getUsername());
            session.setAttribute("jwtToken",jwtToken);
            try {
                return Response.seeOther(new URI("areaCliente/")).build();
            } catch (URISyntaxException e) {
                throw new RuntimeException();
            }
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario e Senha invalidos!").build();
        }
    }

    public Usuario verificaUsuarioServlet(String username,String password){
        Usuario usuario = verificaUsuarioNull(username,password);
        if(usuario==null){
            throw new NotFoundException("Usuario Não Encontrado");
        }else {
            return usuario;
        }
    }


    private Usuario verificaUsuarioNull(String username,String password){
        Usuario credenciaisNoBanco=usuarioDAO.buscarPorUser(username);
        if(credenciaisNoBanco==null) {
            return null;
        }else if(credenciaisNoBanco.getUsername().equals(username) && credenciaisNoBanco.getPassword().equals(password)){
            return credenciaisNoBanco;
        }
        return null;
    }




}
