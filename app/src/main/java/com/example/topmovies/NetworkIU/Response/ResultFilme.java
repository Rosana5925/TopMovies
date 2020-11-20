package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

import java.util.List;

public class ResultFilme {

    @Json(name = "results")
    private final List<ResponseFilme> resultadofilmes;


    public ResultFilme(List<ResponseFilme> resultadofilmes) {
        this.resultadofilmes = resultadofilmes;
    }

    public List<ResponseFilme> getResultadofilmes() {
        return resultadofilmes;
    }
}
