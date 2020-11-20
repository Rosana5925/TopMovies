package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

import java.util.List;

public class ResultTrailer {

    @Json(name = "results")
    private final List<ResponseTrailer> resultadotrailer;

    public ResultTrailer(List<ResponseTrailer> resultadotrailer) {
        this.resultadotrailer = resultadotrailer;
    }

    public List<ResponseTrailer> getResultadotrailer() {
        return resultadotrailer;
    }


}
