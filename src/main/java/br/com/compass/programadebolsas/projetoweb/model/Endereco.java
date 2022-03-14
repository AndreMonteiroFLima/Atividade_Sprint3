package br.com.compass.programadebolsas.projetoweb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="enderecos")
public class Endereco {
	
	@Id
	private int CEP;
	private String rua;
	private int n;
	private String cidade;
	@OneToOne
	private Estado estado;
	
	public Endereco() {

	}
	public int getCEP() {
		return CEP;
	}
	public void setCEP(int cEP) {
		CEP = cEP;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return String.format("  CEP: %d%n  Cidade:%s%n  Lugadouro: %s N°: %d%n Estado:%s ",this.CEP,this.cidade,this.rua,this.n,this.estado.getNome());
	}
	
}
