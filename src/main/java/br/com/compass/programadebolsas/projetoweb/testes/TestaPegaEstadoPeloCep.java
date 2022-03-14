package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.dao.DistanciaEstadoDAO;
import br.com.compass.programadebolsas.projetoweb.dao.EstadoDAO;
import br.com.compass.programadebolsas.projetoweb.model.DistanciaEstado;
import br.com.compass.programadebolsas.projetoweb.model.Estado;

public class TestaPegaEstadoPeloCep {
    public static void main(String[] args) {
        EstadoDAO dao = new EstadoDAO();
        DistanciaEstadoDAO dufdao = new DistanciaEstadoDAO();

        Estado uf=dao.pegaEstadoPeloCep(29018600);
        System.out.println(uf.getNome());

        uf= EstadoDAO.pegaEstadoPeloCep(29018600);

        System.out.println(uf.getNome());

        uf= EstadoDAO.pegaEstadoPeloCep(27018500);

        System.out.println(uf.getNome());

        DistanciaEstado duf= dufdao.pegaDistancia("ES","ES");
        System.out.println(duf.getDistancia());
    }
}
