package br.com.compass.programadebolsas.projetoweb.resources;


import br.com.compass.programadebolsas.projetoweb.filter.Authorize;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.model.Pedido;
import br.com.compass.programadebolsas.projetoweb.model.Produto;
import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import br.com.compass.programadebolsas.projetoweb.services.*;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;


@Path("cadastro")
@Authorize
public class CadastroResource {
    @Context private HttpServletRequest HttpRequest;
    @Context private HttpServletResponse HttpResponse;
    @Context
    SecurityContext securityContext;
    @Context HttpSession session;


    private CadastroService cadastroService = new CadastroService();
    private LoginService loginService = new LoginService();
    private ClienteService clienteService = new ClienteService();
    private UsuarioService usuarioService = new UsuarioService();
    private PedidoService pedidoService= new PedidoService();

    @Authorize
    @Path("/cadastroNovoPedido")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fazCadastro(@FormParam("cep") String cep,
                                @FormParam("rua") String rua,
                                @FormParam("numero") String numero,
                                @FormParam("cidade") String cidade,
                                @FormParam("cepDestino") String cepDestino,
                                @FormParam("produtos[]") List<String> produtos){

        session= HttpRequest.getSession();
        String username = session.getAttribute("usuarioLogado").toString();
        Usuario user = usuarioService.buscarPorUser(username);

        String pedidojson = cadastroService.preencheDadosForm(user.getCliente(),produtos,cep,rua,numero,cidade,cepDestino);


        return Response.ok().entity(pedidojson).build();
    }


    @Path("/cadastroNovoPedido")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fazCadastroJson(String pedidoJson){
        Pedido pedido = (Pedido) new Gson().fromJson(pedidoJson, Pedido.class);
        pedido=pedidoService.cadastrar(pedido);
        return Response.ok().entity(pedido.toGson()).build();
    }

    @Authorize
    @Path("/cadastroNovoUsuario")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fazCadastroUsuario(@FormParam("username") String username,
                                       @FormParam("password") String password,
                                       @FormParam("nomeCliente") String nomeCliente){
        Cliente cliente =  clienteService.preencheCliente(nomeCliente);
        cadastroService.verificaCamposUsuario(username,password,cliente);
        Usuario usuario = usuarioService.preencheUsuario(username,password,cliente);

        return Response.ok().entity(usuario.toJson()).build();
    }


    @Path("/cadastroProduto")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraProduto(@BeanParam Produto produto){
        cadastroService.cadastraProdutoService(produto);
        return Response.ok().entity(produto.toGson()).build();
    }

}
