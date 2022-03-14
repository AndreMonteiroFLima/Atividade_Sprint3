package br.com.compass.programadebolsas.projetoweb.model;

import javax.persistence.*;

@Entity
@Table(name="distanciaestados")
public class DistanciaEstado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=2)
    private String origem;
    @Column(length=2)
    private String destino;
    private long distancia;

    public DistanciaEstado() {
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public long getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }
}
