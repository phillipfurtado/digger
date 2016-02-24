package com.github.phillipfurtado.digger.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
@XmlRootElement
public class Servidor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String nome;

    private String enderecoIP;

    private String user;

    private String senha;

    // 1 - Debian Based, 2 - RedHat Based
    private Integer tipoOS;

    private List<String> aplicacoes;

    public Servidor() {
        super();
    }

    public Servidor(String nome, String enderecoIP) {
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

    public Integer getTipoOS() {
        return tipoOS;
    }

    public void setTipoOS(Integer tipoOS) {
        this.tipoOS = tipoOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
