package br.com.compass.programadebolsas.projetoweb.services;

import br.com.compass.programadebolsas.projetoweb.dao.EnderecoDAO;
import br.com.compass.programadebolsas.projetoweb.dao.EstadoDAO;
import br.com.compass.programadebolsas.projetoweb.exception.CepInexistenteException;
import br.com.compass.programadebolsas.projetoweb.model.Endereco;
import br.com.compass.programadebolsas.projetoweb.model.Estado;
import br.com.compass.programadebolsas.projetoweb.util.CepValidatorUtil;


public class EnderecoService {
    private EnderecoDAO enderecoDAO;
    
    public Endereco preencheEndereco(String cep,String rua,String numero,String cidade){

        enderecoDAO = new EnderecoDAO();

        String CEP = cep;
        CEP=CEP.replaceAll("-", "");

        int cepInt =Integer.valueOf(CEP);
        Endereco endereco = enderecoDAO.validaCEP(cepInt);
        if(CepValidatorUtil.isValid(cep)) {
            if(endereco==null) {
                endereco = new Endereco();
                endereco.setCEP(cepInt);
                endereco.setRua(rua);
                endereco.setN(Integer.valueOf(numero));
                endereco.setCidade(cidade);
                Estado estado = EstadoDAO.pegaEstadoPeloCep(Integer.valueOf(CEP));
                endereco.setEstado(estado);
                enderecoDAO.cadastrar(endereco);
                enderecoDAO.commit();

            }
        }else
            throw new CepInexistenteException();

        return endereco;
    }

    public Endereco preencheEstado(Endereco endereco){

        enderecoDAO = new EnderecoDAO();

        Endereco novoEndereco = enderecoDAO.validaCEP(endereco.getCEP());
        if(novoEndereco==null) {
            String cep = String.valueOf(endereco.getCEP());
            if(CepValidatorUtil.isValid(cep)){
                novoEndereco= new Endereco();
                Estado estado = EstadoDAO.pegaEstadoPeloCep(endereco.getCEP());
                endereco.setEstado(estado);
                enderecoDAO.cadastrar(endereco);
                enderecoDAO.commit();
            }else
                throw new CepInexistenteException();
            return endereco;
        }else
            return novoEndereco;
    }
}

