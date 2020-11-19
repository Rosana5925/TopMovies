package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

public class ResponseFilme {
    @Json(name="id")
    private final int id;
    @Json(name = "poster_path")
    private final String caminhoPoster;
    @Json(name = "original_title")
    private final String tituloOriginal;
    @Json(name ="vote_average")
    private final String voto;
    @Json(name="overview")
    private final String visaogeral;
    @Json(name ="release_date")
    private final String lacamento;


    public String getCaminhoPoster() {
        return caminhoPoster;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getVoto() {
        return voto;
    }

    public String getVisaogeral() { return visaogeral; }

    public String getLacamento() { return lacamento; }

    public int getId() {
        return id;
    }

    public ResponseFilme(int id, String caminhoPoster, String tituloOriginal, String voto, String visaogeral, String lacamento) {
        this.id=id;
        this.caminhoPoster = caminhoPoster;
        this.tituloOriginal = tituloOriginal;
        this.voto=voto;
        this.visaogeral=visaogeral;
        this.lacamento=lacamento;
    }
}
