package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.util.CorreiosUtil;
import br.com.compass.programadebolsas.projetoweb.util.XmlParserUtil;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.List;

public class TestaApiCorreios {
    public static void main(String[] args) {
        String xml =CorreiosUtil.fazConsulta(7365020,04205000);
        System.out.println("\n\n\n"+xml+"\n\n\n");
        List<String> resultado = XmlParserUtil.parseValorEPrazoXML(xml);
        if(resultado.isEmpty())
            System.out.println("Ta vazio irmão");
        else
            System.out.println(resultado);


        String numeroString = resultado.get(0);
        numeroString=numeroString.replace(",",".");
        double numero = Double.valueOf(numeroString);
        System.out.println(numero);
        System.out.println(BigDecimal.valueOf(numero));

    }
}
