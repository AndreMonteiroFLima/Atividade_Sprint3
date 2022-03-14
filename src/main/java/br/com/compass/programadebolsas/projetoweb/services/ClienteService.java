package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.ClienteDAO;
import br.com.compass.programadebolsas.projetoweb.exception.CampoNuloException;
import br.com.compass.programadebolsas.projetoweb.model.Cliente;


public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public static Cliente preencheCliente(String nome){
        Cliente cliente = new Cliente();
        if(nome.isEmpty() || nome.isBlank())
            throw new CampoNuloException("O nome do Usuario não pode ser nulo!");
        cliente.setNome(nome);

        ClienteDAO cDao = new ClienteDAO();
        cDao.cadastrar(cliente);
        cDao.commit();

        return cliente;
    }


}
