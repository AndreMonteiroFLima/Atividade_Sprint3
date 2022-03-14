package br.com.compass.programadebolsas.projetoweb.resources;

import br.com.compass.programadebolsas.projetoweb.model.Endereco;
import br.com.compass.programadebolsas.projetoweb.model.Pedido;
import br.com.compass.programadebolsas.projetoweb.services.PedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;


@Path("Pedido")

public class PedidoResource {
    private PedidoService pedidoService = new PedidoService();


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProdutoId(@NotNull(message = "Id é obrigatorio") @PathParam("id") int id){
        Pedido pedido = pedidoService.listaProdutoIdService(id);
        return Response.ok().entity(pedido.toGson()).build();
    }

   // @Authorize
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaTodosProdutos(){
        String pedidosJson = pedidoService.listaTodosProdutosService();
        return Response.ok().entity(pedidosJson).build();
    }


    @Path("/{id}/produtos/{produtoId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response trocaProduto(@PathParam("id") int id, @PathParam("produtoId") String idProduto){
        Pedido pedido = pedidoService.trocaProdutoService(id, idProduto);
        return Response.ok().entity(pedido.toGson()).build();
    }



    @Path("/{id}/produtos/{produtoId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProduto(@PathParam("id") int id, @PathParam("produtoId") int idProduto){
        Pedido pedido = pedidoService.removeProdutoService(id, idProduto);
        return Response.ok().entity(pedido.toGson()).build();
    }


    @Path("/{id}/endereco")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraEndereco(String conteudo,@PathParam("id") int id){
        Pedido pedido = pedidoService.alteraEnderecoService(conteudo, id);
        return Response.ok().entity(pedido.toGson()).build();
    }


    @Path("/{id}/endereco")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostraEndereco(@PathParam("id") int  id){
        Endereco endereco = pedidoService.mostraEnderecoService(id);
        return Response.ok().entity(endereco).build();
    }

    @Path("/deleta/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaPedidoView(@PathParam("id") int  id){
        pedidoService.deletaPedidoService(id);
        try {
            return Response.seeOther(new URI("http://localhost:8080/ProjetoIndividualWeb2/webapp/areaCliente")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }



}
