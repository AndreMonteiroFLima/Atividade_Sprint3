package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.exception.idNaoEncontradoException;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;
import br.com.compass.programadebolsas.projetoweb.model.Pedido;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDAO {
	private static EntityManager em = JPAUtil.getEntityManager();;

	public PedidoDAO() {
		
	}
	
	 public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}
	
	 public void alterar(Pedido pedido) {
		pedido = em.merge(pedido);
		em.persist(pedido);
	}
	
	 public void deletar(Pedido pedido) {
		pedido = em.merge(pedido);
		em.remove(pedido);
	}
	
	 public Pedido buscarPorId(int id) {
		Pedido pedido = em.find(Pedido.class,id);
		if(pedido==null)
			throw new idNaoEncontradoException("Id "+id+" do pedido não encontrado");
		return pedido;
	}
	
	 public List<Pedido> listaTodosPedidos(){
		String jpql = "SELECT p FROM Pedido AS p";
		return em.createQuery(jpql,Pedido.class).getResultList();
	}

	public List<Pedido> pedidosPorCliente(Cliente cliente){
		String jpql = "SELECT p FROM Pedido AS p WHERE p.cliente.nome=?1";
		return em.createQuery(jpql, Pedido.class).setParameter(1,cliente.getNome()).getResultList();
	}

	 public void commit(){
		em.getTransaction().begin();
		em.getTransaction().commit();
	}
	
}
