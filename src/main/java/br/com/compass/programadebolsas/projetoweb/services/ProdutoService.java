package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.ProdutoDAO;
import br.com.compass.programadebolsas.projetoweb.model.Produto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private ProdutoDAO produtoDAO= new ProdutoDAO();

    public List<Produto> pegaProdutos(List<String> produtosString){
        List<Produto> produtos = new ArrayList<>();
        for (String s: produtosString) {
            produtos.add(produtoDAO.buscarPorId(Integer.valueOf(s)));
        }
        return produtos;
    }

    public void cadastraProduto(Produto produto) {
        produtoDAO.cadastrar(produto);
        produtoDAO.commit();
    }

    public String mostraProdutosService() {
        List<Produto> produtos=produtoDAO.listaTodosProdutos();
        String produtosJson= new Gson().toJson(produtos);
        return produtosJson;
    }

    public List<Produto> pegaProdutosParaMostrarService() {
        List<Produto> produtos=produtoDAO.listaTodosProdutos();
        return produtos;
    }

    public Produto mostraProdutoService(int id) {
        Produto produto= produtoDAO.buscarPorId(id);
        return produto;
    }

    public void deletProdutoService(int id) {
        produtoDAO.deletarId(id);
    }

}
