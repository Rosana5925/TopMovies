package com.example.topmovies.UI;

import com.example.topmovies.entidades.Filme;

import java.util.List;

public interface ListaFilmesContrato {

    interface ListaFilmesView{
        void mostrarFilmes(List<Filme> filmes);
        void mostraErro();

    }

    interface ListaFilmesPresenter{

        void obtemFilmes();

        void destruirView();
    }
}
