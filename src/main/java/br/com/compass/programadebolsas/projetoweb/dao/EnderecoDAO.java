package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.exception.CepInexistenteException;
import br.com.compass.programadebolsas.projetoweb.model.Endereco;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;

public class EnderecoDAO {
	private EntityManager em;

	public EnderecoDAO() {
		em = JPAUtil.getEntityManager();
	}

	public void cadastrar(Endereco endereco) {
		em.persist(endereco);
	}
	
	public void alterar(Endereco endereco) {
		endereco = em.merge(endereco);
		em.persist(endereco);
	}
	
	public void deletar(Endereco endereco) {
		endereco = em.merge(endereco);
		em.remove(endereco);
	}
	
	public Endereco buscarPorCEP(int CEP) {
		Endereco endereco = em.find(Endereco.class,CEP);
		if(endereco==null)
			throw new CepInexistenteException();
		return endereco;
	}
	
	public Endereco validaCEP(int CEP){
		Endereco end = em.find(Endereco.class, CEP);
		if(end == null)
			return null;
		else
			return end;
	}

	public void commit(){
		em.getTransaction().begin();
		em.getTransaction().commit();
	}
	
}
