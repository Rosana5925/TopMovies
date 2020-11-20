package com.example.topmovies.mapper;

import com.example.topmovies.NetworkIU.Response.ResponseReview;
import com.example.topmovies.entidades.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
    public static List<Review> ParaReview(List<ResponseReview> listareviewResponse) {
        List<Review> listaReview = new ArrayList<>();
        if (listareviewResponse != null) {

            for (ResponseReview responseReview : listareviewResponse) {
                final Review review = new Review(responseReview.getAutor(), responseReview.getConteudo());
                listaReview.add(review);
            }
        }
        return listaReview;
    }
}
