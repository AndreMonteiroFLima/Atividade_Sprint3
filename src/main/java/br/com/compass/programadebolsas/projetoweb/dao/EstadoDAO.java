package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.model.Estado;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class EstadoDAO {
	private static EntityManager em = JPAUtil.getEntityManager();

	public EstadoDAO() {

	}
	
	 public static void cadastrar(Estado estado){
		 em.persist(estado);
	}
	
	 public static void alterar(Estado estado) {
		estado = em.merge(estado);
		em.persist(estado);
	}
	
	 public static void deletar(Estado estado) {
		estado = em.merge(estado);
		em.remove(estado);
	}
	
	 public static Estado buscarPorUF(String uf) {
		return em.find(Estado.class,uf);
	}
	
	 public static List<String> listaTodosEstados(){
		String jpql = "SELECT uf.UF FROM Estado AS uf";
		return em.createQuery(jpql,String.class).getResultList();
	}

	 public static Estado pegaEstado(String uf){
		return em.find(Estado.class,uf);
	}
	
	//Colocar Exceção
	 public static boolean validaUF(String uf) {
		String jpql = "SELECT unidade.UF FROM Estado AS unidade WHERE unidade.UF=?1";
		try {
			String estado = em.createQuery(jpql,String.class).setParameter(1, uf).getSingleResult();
			return true;
		}catch(NoResultException e) {
			return false;
		}
	}

	 public static Estado pegaEstadoPeloCep(int cep){
		String jpql = "SELECT uf FROM Estado AS uf WHERE ?1>uf.cepInicio AND ?2<uf.cepFinal";
		return em.createQuery(jpql,Estado.class).setParameter(1,cep).setParameter(2,cep).getSingleResult();
	}
	
	 public static void commit() {
		em.getTransaction().begin();
		em.getTransaction().commit();
	}
	
}
