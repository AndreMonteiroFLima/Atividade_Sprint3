package br.com.compass.programadebolsas.projetoweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
public class Estado {
	
	@Id
	@Column(length = 2)
	private String UF;
	private String Nome;
	private int cepInicio;
	private int cepFinal;


	public Estado() {
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getCepInicio() {
		return cepInicio;
	}
	public void setCepInicio(int cepInicio) {
		this.cepInicio = cepInicio;
	}
	public int getCepFinal() {
		return cepFinal;
	}
	public void setCepFinal(int cepFinal) {
		this.cepFinal = cepFinal;
	}
}
