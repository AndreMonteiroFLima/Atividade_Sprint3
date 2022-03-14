package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.dao.ProdutoDAO;
import br.com.compass.programadebolsas.projetoweb.model.Produto;

import java.io.File;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class InsereProdutosTeste {

    public static void main(String[] args) {
        leArquivoProdutos();
    }

    public static void leArquivoProdutos() {
        try {
            Scanner arquivo = new Scanner(new File("C:\\Users\\Andre\\eclipse-workspace\\ProjetoIndividualWeb2\\src\\produtos.csv"));
            ProdutoDAO produtoDAO = new ProdutoDAO();

            while(arquivo.hasNext()) {

                String linha = arquivo.nextLine();
                Produto produto = new Produto();

                Scanner leitorDeLinhas = new Scanner(linha);
                leitorDeLinhas.useLocale(Locale.US);
                leitorDeLinhas.useDelimiter(",");

                produto.setNome(leitorDeLinhas.next());
                produto.setDescricao(leitorDeLinhas.next());
                double valor = leitorDeLinhas.nextDouble();
                produto.setValor(BigDecimal.valueOf(valor));


                leitorDeLinhas.close();
                produtoDAO.cadastrar(produto);
                produtoDAO.commit();
            }

            arquivo.close();
        }catch(Exception e) {
            System.out.println("Erro:"+ e);
        }
    }
}
