package br.com.compass.programadebolsas.projetoweb.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Context;

public class HttpUtils {

    @Context
    private HttpSession session;


    public HttpSession getSession(HttpServletRequest servletRequest){
        return servletRequest.getSession();
    }

    public String pegaAtributoSession(String attribute,HttpServletRequest httpServletRequest){
        session= new HttpUtils().getSession(httpServletRequest);
        String username = session.getAttribute(attribute).toString();
        return username;
    }

    public void colocaAtributoSession(String nome, String atributo){
        session.setAttribute(nome,atributo);
    }

}
