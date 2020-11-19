package com.example.topmovies.mapper;

import com.example.topmovies.NetworkIU.Response.ResponseFilme;
import com.example.topmovies.NetworkIU.Response.ResponseTrailer;
import com.example.topmovies.entidades.Filme;
import com.example.topmovies.entidades.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerMapper {

    public static List<Trailer> ParaTrailer(List<ResponseTrailer> listatrailerResponse){
        List<Trailer> listaTrailer= new ArrayList<>();
        if(listatrailerResponse != null) {

            for (ResponseTrailer responseTrailer : listatrailerResponse) {
                final Trailer trailer = new Trailer(responseTrailer.getId()
                        , responseTrailer.getChave()
                        , responseTrailer.getNome());
                listaTrailer.add(trailer);
            }
        }
        return listaTrailer;
    }
}
