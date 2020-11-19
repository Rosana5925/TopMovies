package com.example.topmovies.entidades;

import java.io.Serializable;

public class Trailer implements Serializable {
    public String id;
    public String chave;
    public String nome;


    public Trailer(String id, String chave, String nome) {
        this.id = id;
        this.chave = chave;
        this.nome = nome;
    }


    public String getChave() {
        return chave;
    }

    public String getNome() {
        return nome;
    }
}
