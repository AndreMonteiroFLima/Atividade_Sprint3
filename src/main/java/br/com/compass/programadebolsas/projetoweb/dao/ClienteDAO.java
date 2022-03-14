package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {
	private static EntityManager em = JPAUtil.getEntityManager();
	
	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}
	
	public void alterar(Cliente cliente) {
		cliente = em.merge(cliente);
		em.persist(cliente);
	}
	
	public void deletar(Cliente cliente) {
		cliente = em.merge(cliente);
		em.remove(cliente);
	}
	
	public Cliente buscarPorId(int id) {
		return em.find(Cliente.class,id);
	}
	
	public List<Cliente> listaTodosClientes(){
		String jpql = "SELECT c FROM Cliente AS c";
		return em.createQuery(jpql,Cliente.class).getResultList();
	}

	public void commit(){
		em.getTransaction().begin();
		em.getTransaction().commit();
	}


}
