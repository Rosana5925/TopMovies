package com.example.topmovies.entidades;

import java.io.Serializable;

public class Filme implements Serializable {
    private int id;
    private String titulo;
    private String caminhoPoster;
    private String voto;
    private String visaogeral;
    private String lancamento;

    public Filme() {
    }


    public Filme(int id, String titulo, String caminhoPoster, String voto, String visaogeral, String lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.caminhoPoster = caminhoPoster;
        this.voto = voto;
        this.visaogeral = visaogeral;
        this.lancamento = lancamento;

    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCaminhoPoster() {
        return caminhoPoster;
    }

    public String getVoto() {
        return voto;
    }

    public String getVisaogeral() {
        return visaogeral;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCaminhoPoster(String caminhoPoster) {
        this.caminhoPoster = caminhoPoster;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public void setVisaogeral(String visaogeral) {
        this.visaogeral = visaogeral;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }
}


