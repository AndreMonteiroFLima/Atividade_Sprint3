package br.com.compass.programadebolsas.projetoweb.testes;

import br.com.compass.programadebolsas.projetoweb.util.CepValidatorUtil;

public class TestaCepValidator {
    public static void main(String[] args) {
            System.out.println(CepValidatorUtil.isValid("290100"));

        System.out.println(CepValidatorUtil.isValid("29018-600"));
    }
}
