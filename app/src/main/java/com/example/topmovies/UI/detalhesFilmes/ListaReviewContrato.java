package com.example.topmovies.UI.detalhesFilmes;

import com.example.topmovies.entidades.Review;

import java.util.List;

public interface ListaReviewContrato {

    interface ListaReviewView{
        void mostrarReview(List<Review> reviews);
        void mostraErro();

    }

    interface ListaReviewPresenter{

        void obtemReview( int movie_id);

        void destruirView();
    }
}
