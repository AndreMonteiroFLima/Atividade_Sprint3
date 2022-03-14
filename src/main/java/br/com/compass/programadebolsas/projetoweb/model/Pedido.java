package br.com.compass.programadebolsas.projetoweb.model;

import com.google.gson.Gson;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Endereco endereco;
	int CEPdestino;
	private BigDecimal valorFrete;
	private BigDecimal valorPedido;
	int prazoEntrega;
	@ManyToMany
	private List<Produto> produtos;
	
	public Pedido() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getCEPdestino() {
		return CEPdestino;
	}

	public void setCEPdestino(int cEPdestino) {
		CEPdestino = cEPdestino;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public void setValorFrete(String valorFrete) {
		if(valorFrete.isEmpty() || valorFrete.isBlank())
			throw new InputMismatchException("valor Frete Vazio");
		double valorDouble = Double.valueOf(valorFrete);
		this.valorFrete=BigDecimal.valueOf(valorDouble);
	}

	public BigDecimal getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido;
	}

	public void setValorPedido(String valorPedido) {
		if(valorPedido.isEmpty() || valorPedido.isBlank())
			throw new InputMismatchException("valor Frete Vazio");
		double valorDouble = Double.valueOf(valorPedido);
		this.valorPedido=BigDecimal.valueOf(valorDouble);
	}

	public int getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(int prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public void trocaProduto(Produto velho,Produto novo){
		produtos.remove(velho);
		this.addProduto(novo);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void addProduto(Produto produto){
		produtos.add(produto);
	}

	public void removeProduto(Produto produto){
		produtos.remove(produto);
	}
	public void removeProdutoPorId(int produtoId){
		for (Produto p:produtos) {
			if(p.getId()==produtoId)
				produtos.remove(p);
		}
	}


	public String toGson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public String toString() {
		return String.format("Pedido: %n Cliente: %s%n Endereço: %s%n Produtos %s%n  CEP Destino: %d%n Valor do Frete: %.2f%n Prazo de Entrega: %d%n ",
				this.cliente.getNome(),this.endereco,this.produtos,this.CEPdestino,this.valorFrete,this.prazoEntrega);
	}
	
	
}
