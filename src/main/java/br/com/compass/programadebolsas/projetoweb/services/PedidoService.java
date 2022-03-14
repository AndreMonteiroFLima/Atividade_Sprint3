package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.PedidoDAO;
import br.com.compass.programadebolsas.projetoweb.dao.ProdutoDAO;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.model.Endereco;
import br.com.compass.programadebolsas.projetoweb.model.Pedido;
import br.com.compass.programadebolsas.projetoweb.model.Produto;
import br.com.compass.programadebolsas.projetoweb.util.CorreiosUtil;
import br.com.compass.programadebolsas.projetoweb.util.XmlParserUtil;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;


public class PedidoService {
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ProdutoDAO produtoDAO =new ProdutoDAO();

    public Pedido listaProdutoIdService(int id) {
        Pedido pedido=pedidoDAO.buscarPorId(id);
        return pedido;
    }

    public String listaTodosProdutosService() {
        List<Pedido> pedidos = pedidoDAO.listaTodosPedidos();
        String pedidosJson=new Gson().toJson(pedidos);
        return pedidosJson;
    }

    public Pedido preenchePedido(Cliente cliente, Endereco endereco, List<Produto> produtos, String cepDestino){
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        pedido.setEndereco(endereco);

        pedido.setProdutos(produtos);
        calculaValorTotal(pedido);
        pedido.setCEPdestino(Integer.valueOf(cepDestino));
        String xml =CorreiosUtil.fazConsulta(pedido.getEndereco().getCEP(),pedido.getCEPdestino());
        List<String> valorEPrazo = XmlParserUtil.parseValorEPrazoXML(xml);

        String valor = valorEPrazo.get(0).replace(",",".");
        pedido.setValorFrete(valor);
        pedido.setPrazoEntrega(Integer.valueOf(valorEPrazo.get(1)));

        pedidoDAO.cadastrar(pedido);
        pedidoDAO.commit();

        return pedido;
    }

    private void calculaValorTotal(Pedido pedido){
        double total=0;
        for (Produto p:pedido.getProdutos()) {
            total+=p.getValor().doubleValue();
        }
        pedido.setValorPedido(BigDecimal.valueOf(total));
    }

    public Pedido cadastrar(Pedido pedido){
        pedidoDAO.cadastrar(pedido);
        pedidoDAO.commit();
        return pedido;
    }

    public Pedido trocaProdutoService(int id,String idProduto) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        String[] array = idProduto.split("-");
        int produtoVelho=Integer.valueOf(array[0]);
        int produtoNovo=Integer.valueOf(array[1]);
        Produto velho = produtoDAO.buscarPorId(produtoVelho);
        Produto novo = produtoDAO.buscarPorId(produtoNovo);
        pedido.trocaProduto(velho,novo);

        pedidoDAO.alterar(pedido);
        pedidoDAO.commit();
        return pedido;
    }

    public Pedido removeProdutoService(int id, int idProduto) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        pedido.removeProdutoPorId(idProduto);
        pedidoDAO.alterar(pedido);
        pedidoDAO.commit();
        return pedido;
    }

    public Pedido alteraEnderecoService(String conteudo, int id) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        Endereco endereco = (Endereco) new Gson().fromJson(conteudo, Endereco.class);
        pedido.setEndereco(endereco);
        pedidoDAO.alterar(pedido);
        pedidoDAO.commit();
        return pedido;
    }

    public Endereco mostraEnderecoService(int id) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        Endereco endereco = pedido.getEndereco();
        return endereco;
    }

    public void deletaPedidoService(int id) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        pedidoDAO.deletar(pedido);
        pedidoDAO.commit();
    }

}
