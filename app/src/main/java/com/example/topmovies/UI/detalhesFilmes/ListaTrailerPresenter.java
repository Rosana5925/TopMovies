package com.example.topmovies.UI.detalhesFilmes;

import androidx.appcompat.app.AppCompatActivity;

import com.example.topmovies.BuildConfig;
import com.example.topmovies.NetworkIU.Response.ResultTrailer;
import com.example.topmovies.NetworkIU.ServiceApi;
import com.example.topmovies.entidades.Trailer;
import com.example.topmovies.mapper.TrailerMapper;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaTrailerPresenter implements ListaTrailerContrato.ListaTrailerPresenter {
    private ListaTrailerContrato.ListaTrailerView view;
    public static final String Extra_Trailer = "Extra Trailer";


    public ListaTrailerPresenter(ListaTrailerContrato.ListaTrailerView view) {
        this.view = view;
    }

    @Override
    public void obtemTrailer( int movie_id) {

        ServiceApi.getInstance().obterTrailer(movie_id, BuildConfig.MY_API_KEY).enqueue(new Callback<ResultTrailer>() {
            @Override
            public void onResponse(Call<ResultTrailer> call, Response<ResultTrailer> response) {
                if (response.isSuccessful()) {
                    List<Trailer> listatrailer = TrailerMapper.ParaTrailer(response.body().getResultadotrailer());
                    view.mostrarTrailer(listatrailer);
                } else {
                    view.mostraErro();
                }
            }

            @Override
            public void onFailure(Call<ResultTrailer> call, Throwable t) {
                view.mostraErro();

            }
        });

    }
    @Override
    public void destruirView() {
        this.view = null;

    }
}
