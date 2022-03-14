package br.com.compass.programadebolsas.projetoweb.resources;

import br.com.compass.programadebolsas.projetoweb.dao.PedidoDAO;
import br.com.compass.programadebolsas.projetoweb.dao.UsuarioDAO;
import br.com.compass.programadebolsas.projetoweb.filter.Authorize;
import br.com.compass.programadebolsas.projetoweb.model.Pedido;
import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

import java.io.IOException;
import java.util.List;

@Path("areaCliente")
@Authorize
public class AreaClienteResource {
    
    @Path("/")
    @GET
    public void mostraAreaCliente(@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse){

        HttpSession session= httpRequest.getSession();
        UsuarioDAO usuarioDAO=new UsuarioDAO();
        Usuario user= usuarioDAO.buscarPorUser(session.getAttribute("usuarioLogado").toString());
        List<Pedido> pedidos = new PedidoDAO().pedidosPorCliente(user.getCliente());


        httpRequest.setAttribute("pedidos",pedidos);
        httpRequest.setAttribute("username",user.getUsername());
        RequestDispatcher rd = httpRequest.getRequestDispatcher("/AreaCliente.jsp");
        try {
            rd.forward(httpRequest,httpResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
