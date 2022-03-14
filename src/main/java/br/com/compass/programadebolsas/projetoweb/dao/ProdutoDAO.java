package br.com.compass.programadebolsas.projetoweb.dao;

import br.com.compass.programadebolsas.projetoweb.exception.idNaoEncontradoException;
import br.com.compass.programadebolsas.projetoweb.model.Produto;
import br.com.compass.programadebolsas.projetoweb.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {

    private static EntityManager em = JPAUtil.getEntityManager();

    public void cadastrar(Produto produto) {
        em.persist(produto);
    }

    public void alterar(Produto produto) {
        produto = em.merge(produto);
        em.persist(produto);
    }

    public void deletar(Produto produto) {
        produto = em.merge(produto);
        em.remove(produto);
    }
    public void deletarId(int id) {
        String jpql = "DELETE FROM Produto AS p WHERE p.id=?1";
        em.createQuery(jpql,Produto.class).setParameter(1,id);
    }

    public Produto buscarPorId(int id) {
        Produto produto = em.find(Produto.class, id);
        if(produto==null)
            throw new idNaoEncontradoException("Produto de id "+ id +" não encontrado");
        return produto;
    }

    public List<Produto> listaTodosProdutos(){
        String jpql = "SELECT p FROM Produto AS p";
        return em.createQuery(jpql,Produto.class).getResultList();
    }

    public void commit(){
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
    
}
