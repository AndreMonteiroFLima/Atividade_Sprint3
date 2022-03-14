package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.exception.CampoNuloException;
import br.com.compass.programadebolsas.projetoweb.model.*;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class CadastroService {

    private PedidoService pedidoService = new PedidoService();
    private EnderecoService enderecoService = new EnderecoService();
    private ClienteService clienteService = new ClienteService();
    private ProdutoService produtoService = new ProdutoService();

    public String preencheDadosForm(Cliente cliente, List<String> produtos, String cep, String rua, String numero, String cidade, String cepDestino){

        if(produtos.isEmpty() || cep.isEmpty() || rua.isEmpty() || numero.isEmpty() || cidade.isEmpty() || cepDestino.isEmpty())
            throw new CampoNuloException("Um dos campos do pedido esta nulo!");
        else {
            List<Produto> produtosPreenchidos = produtoService.pegaProdutos(produtos);
            Endereco endereco = enderecoService.preencheEndereco(cep, rua, numero, cidade);
            Pedido pedido = pedidoService.preenchePedido(cliente, endereco, produtosPreenchidos, cepDestino);

            Entrega entrega = new Entrega(pedido.getValorFrete(), pedido.getPrazoEntrega());
            String pedidojson = new Gson().toJson(entrega);

            return pedidojson;
        }
    }



    public void verificaCamposUsuario(String username,String password,Cliente cliente){
        if(username.isEmpty() || username.isBlank())
            throw new CampoNuloException("O campo Usuario esta Nulo");
        if(password.isEmpty() || password.isBlank())
            throw new CampoNuloException("O campo Senha esta Nulo");
        if(cliente==null)
            throw new CampoNuloException("O campo Nome esta Nulo");

    }

    public void mostraCadastroService(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        List<Produto> produtos = produtoService.pegaProdutosParaMostrarService();

        httpRequest.setAttribute("produtos",produtos);
        RequestDispatcher rd = httpRequest.getRequestDispatcher("/cadastroForm.jsp");
        try {
            rd.forward(httpRequest,httpResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    public void cadastraProdutoService(Produto produto) {
        produtoService.cadastraProduto(produto);
    }
}
