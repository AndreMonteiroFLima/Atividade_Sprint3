package br.com.compass.programadebolsas.projetoweb.resources;

import br.com.compass.programadebolsas.projetoweb.filter.Authorize;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import br.com.compass.programadebolsas.projetoweb.services.*;
import br.com.compass.programadebolsas.projetoweb.util.HttpUtils;
import br.com.compass.programadebolsas.projetoweb.util.UriUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/cadastroView")
public class CadastroResourceView {

    private SecurityContext securityContext;
    private HttpSession session;

    private CadastroService cadastroService = new CadastroService();
    private LoginService loginService = new LoginService();
    private ClienteService clienteService = new ClienteService();
    private UsuarioService usuarioService = new UsuarioService();
    private PedidoService pedidoService= new PedidoService();

    @Authorize
    @Path("/cadastroNovoPedidoView")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response fazCadastroView(@FormParam("cep") String cep,
                                    @FormParam("rua") String rua,
                                    @FormParam("numero") String numero,
                                    @FormParam("cidade") String cidade,
                                    @FormParam("cepDestino") String cepDestino,
                                    @FormParam("produtos[]") List<String> produtos,
                                    @Context HttpServletRequest httpServletRequest){


        String username = new HttpUtils().pegaAtributoSession("usuarioLogado",httpServletRequest);
        Usuario user = usuarioService.buscarPorUser(username);
        String pedidojson = cadastroService.preencheDadosForm(user.getCliente(),produtos,cep,rua,numero,cidade,cepDestino);


        /*URI contextPath= UriUtils.getFullServletContextPath(servletContext,uriInfo);
        UriBuilder builder = UriBuilder.fromUri(contextPath).path("areaCliente/");*/


        try {
            URI uri= new URI("http://localhost:8080/ProjetoIndividualWeb2/webapp/areaCliente");
            return Response.seeOther(uri).build();
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }


    @Path("/mostraCadastro")
    @GET
    public void mostraCadastroProduto(@Context HttpServletRequest httpRequest,
                                      @Context HttpServletResponse httpResponse){
        cadastroService.mostraCadastroService(httpRequest, httpResponse);
    }

    @Path("/mostraCadastroView")
    @GET
    public void mostraCadastroProdutoView(@Context HttpServletRequest httpRequest,
                                      @Context HttpServletResponse httpResponse){
        cadastroService.mostraCadastroService(httpRequest, httpResponse);
    }


    @Path("/mostraUsuario")
    @GET
    public Response mostraCadastroUsuario(@Context ServletContext servletContext, @Context UriInfo uriInfo){
        URI contextPath= UriUtils.getFullServletContextPath(servletContext,uriInfo);
        UriBuilder builder = UriBuilder.fromUri(contextPath).path("cadastroUsuario.jsp");
        return Response.seeOther(builder.build()).build();
    }

    @Path("/cadastroNovoUsuarioView")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response fazCadastroUsuarioView(@FormParam("username") String username,
                                           @FormParam("password") String password,
                                           @FormParam("nomeCliente") String nomeCliente,
                                           @Context ServletContext servletContext,
                                           @Context UriInfo uriInfo){
        Cliente cliente =  clienteService.preencheCliente(nomeCliente);
        cadastroService.verificaCamposUsuario(username,password,cliente);
        Usuario usuario = usuarioService.preencheUsuario(username,password,cliente);

        URI contextPath= UriUtils.getFullServletContextPath(servletContext,uriInfo);
        UriBuilder builder = UriBuilder.fromUri(contextPath).path("cadastroConfirmado.jsp");
        new HttpUtils().colocaAtributoSession("usuarioLogado",usuario.getUsername());
        return Response.seeOther(builder.build()).build();
    }
}
