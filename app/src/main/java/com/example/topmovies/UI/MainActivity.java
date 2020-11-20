package com.example.topmovies.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.DB.FavoriteDBHelper;
import com.example.topmovies.NetworkIU.Response.ResultFilme;
import com.example.topmovies.R;
import com.example.topmovies.UI.detalhesFilmes.DetalhesFilmeActivity;
import com.example.topmovies.entidades.Filme;
import com.example.topmovies.mapper.FilmeMapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements ListaFilmesContrato.ListaFilmesView,
        MyRecyclerViewAdapter.ItemFilmeClickListener {

    private MyRecyclerViewAdapter filmesadapter;
    private ListaFilmesContrato.ListaFilmesPresenter presenter;
    private AppCompatActivity activity = MainActivity.this;
    private FavoriteDBHelper favoriteDBHelper;
    private List<Filme> filmesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configuracaoAdapter();
        presenter = new ListaFilmesPresenter(this);
        presenter.obtemFilmes();
        favoriteDBHelper = new FavoriteDBHelper(activity);

    }


    private void configuracaoAdapter() {
        int numberOfColumns = 2;
        final RecyclerView recyclerViewFilmes = findViewById(R.id.rvlista);
        filmesadapter = new MyRecyclerViewAdapter(this);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerViewFilmes.setLayoutManager(gridLayoutManager);
        recyclerViewFilmes.setAdapter(filmesadapter);

    }


    @Override
    public void mostrarFilmes(List<Filme> filmes) {
        filmesadapter.setFilmes(filmes);

    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de filmes.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destruirView();
    }

    @Override
    public void onItemFilmeClicado(Filme filme) {
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.Extra_Filme, filme);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_populares:
                presenter.obtemFilmes();
                return true;
            case R.id.action_favorito:
                getAllfavoritos();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (filmesList != null) {
            if (filmesList.isEmpty()) {
                // checkSortOrder();
            }
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    private void getAllfavoritos() {

        filmesadapter.setFilmes(
                favoriteDBHelper.getAllFavorite()
        );

    }


}