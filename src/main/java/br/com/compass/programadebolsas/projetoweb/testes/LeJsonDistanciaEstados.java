package br.com.compass.programadebolsas.projetoweb.testes;


import br.com.compass.programadebolsas.projetoweb.dao.DistanciaEstadoDAO;
import br.com.compass.programadebolsas.projetoweb.model.DistanciaEstado;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class LeJsonDistanciaEstados {

    public static void main(String[] args) {
        leArquivo();


    }
    public static void leArquivo() {
        try {
            Scanner arquivo = new Scanner(new File("C:\\Users\\Andre\\eclipse-workspace\\ProjetoIndividualWeb\\src\\estadosDistancia.csv"), "utf-8");


            while(arquivo.hasNext()) {
                DistanciaEstado de = new DistanciaEstado();
                String linha = arquivo.nextLine();
                Scanner leitorDeLinhas = new Scanner(linha);
                leitorDeLinhas.useLocale(Locale.US);
                leitorDeLinhas.useDelimiter(",");

                de.setOrigem(leitorDeLinhas.next());
                de.setDestino(leitorDeLinhas.next());
                de.setDistancia(leitorDeLinhas.nextLong());

                System.out.println(de);
                DistanciaEstadoDAO.cadastrar(de);
                DistanciaEstadoDAO.commit();
                leitorDeLinhas.close();
                }
            arquivo.close();
        }catch(Exception e) {
            System.out.println("Erro:"+ e);
        }
    }
}
