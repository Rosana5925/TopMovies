package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

public class ResponseTrailer {
    @Json(name = "id")
    private final String id;
    @Json(name = "name")
    private final String nome;
    @Json(name = "key")
    private final String chave;

    public ResponseTrailer(String id, String nome, String chave) {
        this.id = id;
        this.nome = nome;
        this.chave = chave;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }
}
