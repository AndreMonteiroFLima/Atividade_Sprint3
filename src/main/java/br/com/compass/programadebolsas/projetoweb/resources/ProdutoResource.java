package br.com.compass.programadebolsas.projetoweb.resources;

import br.com.compass.programadebolsas.projetoweb.model.Produto;
import br.com.compass.programadebolsas.projetoweb.services.ProdutoService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("produto")
public class ProdutoResource {
    private ProdutoService produtoService= new ProdutoService();

    @Path("/")
    @GET
    public Response mostraProdutos(){
        String produtosJson = produtoService.mostraProdutosService();
        return Response.ok().entity(produtosJson).build();
    }

    @Path("/{id}")
    @GET
    public Response mostraProduto(@PathParam("id") int id){
        Produto produto = produtoService.mostraProdutoService(id);
        return Response.ok().entity(produto.toGson()).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deletarProduto(@PathParam("id") int id){
        produtoService.deletProdutoService(id);
        return Response.ok().build();
    }

}
