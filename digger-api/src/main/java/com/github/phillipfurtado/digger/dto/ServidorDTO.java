package com.github.phillipfurtado.digger.dto;

import java.io.Serializable;
import java.util.List;

public class ServidorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String enderecoIP;

    private String user;

    private String senha;

    private List<String> aplicacoes;

    public ServidorDTO(String nome, String enderecoIP) {
        super();
        this.nome = nome;
        this.enderecoIP = enderecoIP;
    }

    public String getEnderecoIP() {
        return enderecoIP;
    }

    public void setEnderecoIP(String enderecoIP) {
        this.enderecoIP = enderecoIP;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getUser() {
        return user;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(List<String> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }

}
