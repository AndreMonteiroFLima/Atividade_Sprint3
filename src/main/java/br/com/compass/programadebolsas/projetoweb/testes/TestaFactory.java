package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;

public class TestaFactory {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();
//		LeitorDeArquivos.leArquivo("./src/main/resources/Estados.csv", ec);
	}

}
