package com.example.topmovies.mapper;

import com.example.topmovies.NetworkIU.Response.ResponseFilme;
import com.example.topmovies.entidades.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeMapper {

    public static List<Filme>  doResponseParaDominio (List<ResponseFilme> listaFilmeResponse){
        List<Filme> listafilmes= new ArrayList<>();

        for(ResponseFilme responseFilme: listaFilmeResponse){
            final  Filme filme=new Filme(responseFilme.getId(),responseFilme.getTituloOriginal()
                    ,responseFilme.getCaminhoPoster()
                    ,responseFilme.getVoto()
                    ,responseFilme.getVisaogeral()
                    ,responseFilme.getLacamento());
            listafilmes.add(filme);
        }
        return listafilmes;
    }
}
