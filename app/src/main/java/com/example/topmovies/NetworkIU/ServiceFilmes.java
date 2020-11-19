package com.example.topmovies.NetworkIU;

import com.example.topmovies.NetworkIU.Response.ResultFilme;
import com.example.topmovies.NetworkIU.Response.ResultTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceFilmes {

    @GET("movie/popular")
    //primeiro edpoint com retrofit
    Call<ResultFilme> obterFilmesPopulares(@Query("api_key") String chaveApi);

    @GET("movie/{id}/videos")
    //segundo edpoint com retrofit
    Call<ResultTrailer> obterTrailer(@Path("id") int id, @Query("api_key") String chaveApi);

}
