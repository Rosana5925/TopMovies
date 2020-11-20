package com.example.topmovies.UI;

import com.example.topmovies.BuildConfig;
import com.example.topmovies.NetworkIU.Response.ResultFilme;
import com.example.topmovies.NetworkIU.ServiceApi;
import com.example.topmovies.entidades.Filme;
import com.example.topmovies.mapper.FilmeMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesPresenter implements ListaFilmesContrato.ListaFilmesPresenter {

    private ListaFilmesContrato.ListaFilmesView view;


    public ListaFilmesPresenter(ListaFilmesContrato.ListaFilmesView view) {
        this.view = view;
    }

    @Override
    public void obtemFilmes() {
        ServiceApi.getInstance().obterFilmesPopulares(BuildConfig.MY_API_KEY)
                .enqueue(new Callback<ResultFilme>() {
                    @Override
                    public void onResponse(Call<ResultFilme> call, Response<ResultFilme> response) {
                        if (response.isSuccessful()) {
                            List<Filme> listafilmes = FilmeMapper
                                    .doResponseParaDominio(response.body().getResultadofilmes());
                            view.mostrarFilmes(listafilmes);
                        } else {
                            view.mostraErro();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResultFilme> call, Throwable t) {
                        view.mostraErro();

                    }
                });
    }

    @Override
    public void destruirView() {
        this.view = null;
    }


}
