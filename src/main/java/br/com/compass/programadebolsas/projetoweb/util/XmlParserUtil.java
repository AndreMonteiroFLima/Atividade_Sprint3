package br.com.compass.programadebolsas.projetoweb.util;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParserUtil {

    public static List<String> parseValorEPrazoXML(String str){
        DocumentBuilder builder = null;
        List<String> resultado= new ArrayList<>();
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(str));

            Document doc = builder.parse(src);
            resultado.add(doc.getElementsByTagName("Valor").item(0).getTextContent());
            resultado.add(doc.getElementsByTagName("PrazoEntrega").item(0).getTextContent());

        }catch (ParserConfigurationException| IOException | SAXException e) {
            System.out.println("O parse falhou");
        }
        return resultado;
    }
}
