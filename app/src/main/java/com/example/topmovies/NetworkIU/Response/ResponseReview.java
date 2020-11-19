package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

public class ResponseReview {

    @Json(name = "author")
    private final String autor;

    @Json(name = "content")
    private final String conteudo;


    public ResponseReview(String autor, String conteudo) {
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
