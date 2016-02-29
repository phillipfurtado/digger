package com.github.phillipfurtado.digger.model;

import java.io.Serializable;

public class Aplicacao implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Aplicacao(String nomeapp) {
        this.nome = nomeapp;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
