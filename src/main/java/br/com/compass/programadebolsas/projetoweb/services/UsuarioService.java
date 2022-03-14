package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.UsuarioDAO;
import br.com.compass.programadebolsas.projetoweb.exception.UsuarioExistenteException;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Context;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;
    @Context
    HttpSession session;

    public Usuario preencheUsuario(String username, String password, Cliente cliente){
        usuarioDAO = new UsuarioDAO();
        Usuario usuario=usuarioDAO.buscarPorUser(username);
        if(usuario==null){
            usuario=new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setCliente(cliente);
            usuarioDAO.cadastrar(usuario);
            usuarioDAO.commit();
        }else
            throw new UsuarioExistenteException("");
        return usuario;
    }

    public Usuario buscarPorUser(String user) {
        usuarioDAO=new UsuarioDAO();
        Usuario userBanco=usuarioDAO.buscarPorUser(user);
        if(userBanco==null)
            throw new UsuarioExistenteException("O usuario:"+user+" não existe!");
        return userBanco;

    }

/*    public Usuario retornaUsuario(){

        String username = session.getAttribute("usuarioLogado").toString();
         Usuario user = usuarioService.buscarPorUser(username);
        return user;
    }*/
}
