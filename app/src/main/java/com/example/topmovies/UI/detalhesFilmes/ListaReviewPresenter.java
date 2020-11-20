package com.example.topmovies.UI.detalhesFilmes;

import com.example.topmovies.BuildConfig;
import com.example.topmovies.NetworkIU.Response.ResultReview;
import com.example.topmovies.NetworkIU.Response.ResultTrailer;
import com.example.topmovies.NetworkIU.ServiceApi;
import com.example.topmovies.entidades.Review;
import com.example.topmovies.mapper.ReviewMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaReviewPresenter implements ListaReviewContrato.ListaReviewPresenter {
    private ListaReviewContrato.ListaReviewView view;
    public static final String Extra_Review = "Extra Review";

    public ListaReviewPresenter(ListaReviewContrato.ListaReviewView view) {
        this.view = view;
    }

    @Override
    public void obtemReview(int movie_id) {
        ServiceApi.getInstance().obterReview(movie_id, BuildConfig.MY_API_KEY).enqueue(new Callback<ResultReview>() {
            @Override
            public void onResponse(Call<ResultReview> call, Response<ResultReview> response) {
                if (response.isSuccessful()) {
                    List<Review> reviewList = ReviewMapper.ParaReview(response.body().getReviews());
                    view.mostrarReview(reviewList);
                } else {
                    view.mostraErro();
                }
            }

            @Override
            public void onFailure(Call<ResultReview> call, Throwable t) {
                view.mostraErro();

            }
        });

    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
