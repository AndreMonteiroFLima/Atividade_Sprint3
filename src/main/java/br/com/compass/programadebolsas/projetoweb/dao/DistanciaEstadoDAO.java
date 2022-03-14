package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.model.DistanciaEstado;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class DistanciaEstadoDAO {
    private static EntityManager em = JPAUtil.getEntityManager();

    public DistanciaEstadoDAO() {
    }

    public static void cadastrar(DistanciaEstado distanciaEstado){
        em.persist(distanciaEstado);
    }


    public static void alterar(DistanciaEstado distanciaEstado) {
        distanciaEstado = em.merge(distanciaEstado);
        em.persist(distanciaEstado);
    }

    public static void deletar(DistanciaEstado distanciaEstado) {
        distanciaEstado = em.merge(distanciaEstado);
        em.remove(distanciaEstado);
    }

    public static DistanciaEstado buscarPorUF(String uf) {
        return em.find(DistanciaEstado.class,uf);
    }

    public static List<String> listaTodosEstados(){
        String jpql = "SELECT uf FROM DistanciaEstado AS uf";
        return em.createQuery(jpql,String.class).getResultList();
    }

    public static DistanciaEstado pegaEstado(String uf){
        return em.find(DistanciaEstado.class,uf);
    }

    public static DistanciaEstado pegaDistancia(String uf_origem,String uf_destino){
        String jpql = "SELECT Duf FROM DistanciaEstado AS Duf WHERE Duf.origem=?1 AND Duf.destino=?2";
        return em.createQuery(jpql,DistanciaEstado.class).setParameter(1,uf_origem).setParameter(2,uf_destino).getSingleResult();
    }

    public static void commit() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}
