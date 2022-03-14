package br.com.compass.programadebolsas.projetoweb.model;

import java.math.BigDecimal;

public class Entrega {

    private BigDecimal valor;
    private int prazo;

    public Entrega() {
    }

    public Entrega(BigDecimal valor, int prazo) {
        this.valor = valor;
        this.prazo = prazo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
}
