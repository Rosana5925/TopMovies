package com.example.topmovies.entidades;

import java.io.Serializable;

public class Review implements Serializable {
    private String autor;
    private String conteudo;

    public Review(String autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }
}
