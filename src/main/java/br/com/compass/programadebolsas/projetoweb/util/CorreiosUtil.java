package br.com.compass.programadebolsas.projetoweb.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CorreiosUtil {

    public static String fazConsulta(int cepOrigem,int cepDestino){
        String tipoRetorno = "xml";
        String retorno = null;
        String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?"+"nCdEmpresa=08082650"+"&sDsSenha=564321"+
                "&sCepOrigem="+cepOrigem+"&sCepDestino="+cepDestino+"&nVlPeso=1"+"&nCdFormato=1"+"&nVlComprimento=20"+"&nVlAltura=20"
                +"&nVlLargura=20"+"&sCdMaoPropria=n"+"&nVlValorDeclarado=0"+"&sCdAvisoRecebimento=n"+"&nCdServico=04510"+"&nVlDiametro=0"+
                "&StrRetorno="+tipoRetorno+"&nIndicaCalculo=3";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).headers("Accept","application/xml").build();
        HttpClient httpClient = HttpClient.newBuilder().build();

        try {
            HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            retorno= response.body().toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
