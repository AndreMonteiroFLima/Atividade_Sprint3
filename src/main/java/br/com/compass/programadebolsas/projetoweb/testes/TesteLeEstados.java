package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.dao.EstadoDAO;
import br.com.compass.programadebolsas.projetoweb.model.Estado;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class TesteLeEstados {
    public static void main(String[] args) {
        leArquivo("C:\\Users\\Andre\\eclipse-workspace\\ProjetoIndividualWeb\\src\\main\\resources\\EstadosComCEP.csv");
    }

    public static void leArquivo(String str) {
        try {
            Scanner arquivo = new Scanner(new File(str), "utf-8");
            System.out.println("Lendo o arquivo");

            while(arquivo.hasNext()) {

                String linha = arquivo.nextLine();
                Estado estado = new Estado();

                Scanner leitorDeLinhas = new Scanner(linha);
                leitorDeLinhas.useLocale(Locale.US);
                leitorDeLinhas.useDelimiter(",");

                String sigla = leitorDeLinhas.next();

                if(EstadoDAO.validaUF(sigla))
                    return;
                else {
                    estado.setUF(sigla);
                    String nome = leitorDeLinhas.next();
                    System.out.println(nome);
                    estado.setNome(nome);
                    estado.setCepInicio(leitorDeLinhas.nextInt());
                    estado.setCepFinal(leitorDeLinhas.nextInt());

                    EstadoDAO.cadastrar(estado);
                    EstadoDAO.commit();
                    leitorDeLinhas.close();
                }
            }

            arquivo.close();
        }catch(Exception e) {
            System.out.println("Erro:"+ e);
        }
    }

}


