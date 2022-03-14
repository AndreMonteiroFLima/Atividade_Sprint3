package br.com.compass.programadebolsas.projetoweb.model;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    private String username;
    private String password;
    @OneToOne
    private Cliente cliente;


    public Usuario() {
    }

    public Usuario( String username, String password,Cliente cliente) {
        this.username = username;
        this.password = password;
        this.cliente = cliente;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
