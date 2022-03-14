package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.model.Usuario;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO {
    private static EntityManager em = JPAUtil.getEntityManager();

    public UsuarioDAO() {
    }

    public void cadastrar(Usuario usuario) {
        em.persist(usuario);
    }

    public void alterar(Usuario usuario) {
        usuario = em.merge(usuario);
        em.persist(usuario);
    }

    public void deletar(Usuario usuario) {
        usuario = em.merge(usuario);
        em.remove(usuario);
    }

    public Usuario buscarPorUser(String user) {
        return em.find(Usuario.class,user);
    }

    public List<Usuario> listaTodosUsuarios(){
        String jpql = "SELECT u FROM Usuario AS u";
        return em.createQuery(jpql,Usuario.class).getResultList();
    }

    public void commit(){
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}
