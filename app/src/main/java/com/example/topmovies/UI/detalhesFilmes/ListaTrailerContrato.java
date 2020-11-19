package com.example.topmovies.UI.detalhesFilmes;

import com.example.topmovies.entidades.Trailer;

import java.util.List;

public interface ListaTrailerContrato {

    interface ListaTrailerView{
        void mostrarTrailer(List<Trailer> trailers);
        void mostraErro();

    }

    interface ListaTrailerPresenter{

        void obtemTrailer( int movie_id);

        void destruirView();
    }
}
