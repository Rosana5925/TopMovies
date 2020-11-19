package com.example.topmovies.UI.detalhesFilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topmovies.DB.FavoriteContrat;
import com.example.topmovies.DB.FavoriteDBHelper;
import com.example.topmovies.R;
import com.example.topmovies.UI.ListaFilmesContrato;
import com.example.topmovies.entidades.Filme;
import com.example.topmovies.entidades.Trailer;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetalhesFilmeActivity extends AppCompatActivity implements ListaTrailerContrato.ListaTrailerView {

    public static final String Extra_Filme = "Extra Filme";
    private RecyclerView recyclerView;
    private List<Trailer> trailerList;
    private TrailerAdapter adaptertrailer;
    private ListaTrailerContrato.ListaTrailerPresenter presenter;
    private FavoriteDBHelper favoriteDBHelper;
    private Filme favorito;
    private final AppCompatActivity activity = DetalhesFilmeActivity.this;
    private SQLiteDatabase mDB;

    TextView textTituloFilme;
    TextView textviewVisaogeral;
    TextView textViewVoto;
    Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);


        FavoriteDBHelper dbHelper = new FavoriteDBHelper(this);
        mDB = dbHelper.getWritableDatabase();

      filme = (Filme) getIntent().getSerializableExtra(Extra_Filme);

        textTituloFilme = findViewById(R.id.textViewtitulofilme);
        textTituloFilme.setText(filme.getTitulo());

        textViewVoto = findViewById(R.id.textView_vote);
        textViewVoto.setText(filme.getVoto());

        textviewVisaogeral = findViewById(R.id.textViewVisaoglobal);
        textviewVisaogeral.setText(filme.getVisaogeral());

        TextView textViewLancamento = findViewById(R.id.textViewlancamento);
        textViewLancamento.setText(filme.getLancamento());

        ImageView capafilme = findViewById(R.id.capaFilme);
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342" + filme.getCaminhoPoster())
                .into(capafilme);

        configurarAdapter();
        presenter = new ListaTrailerPresenter(this);
        presenter.obtemTrailer(filme.getId());


        MaterialFavoriteButton materialFavoriteButton =
                (MaterialFavoriteButton) findViewById(R.id.favbuttom);


        if (Exists(filme.getTitulo())) {
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                            if (favorite == true) {
                                saveFavorite();
                                Snackbar.make(buttonView, "Adicionado aos Favoritos.",
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                favoriteDBHelper = new FavoriteDBHelper(DetalhesFilmeActivity.this);
                                favoriteDBHelper.deleteFavorite(filme.getId());
                                Snackbar.make(buttonView, "Removido dos Favoritos.",
                                        Snackbar.LENGTH_SHORT).show();

                            }
                        }
                    });

        }else{
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                    if (favorite == true) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Adicionado aos Favoritos.",
                                Snackbar.LENGTH_SHORT).show();
                    } else {
                        favoriteDBHelper = new FavoriteDBHelper(DetalhesFilmeActivity.this);
                        favoriteDBHelper.deleteFavorite(filme.getId());
                        Snackbar.make(buttonView, "Removido dos Favoritos.",
                                Snackbar.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    public boolean Exists(String searchItem) {
        String[] projection = {
                FavoriteContrat.FavoriteEntry._ID,
                FavoriteContrat.FavoriteEntry.COLUMN_ID,
                FavoriteContrat.FavoriteEntry.COLUMN_TITULO,
                FavoriteContrat.FavoriteEntry.COLUMN_VOTO,
                FavoriteContrat.FavoriteEntry.COLUMN_POSTER,
                FavoriteContrat.FavoriteEntry.COLUMN_VGERAL};

        String selection = FavoriteContrat.FavoriteEntry.COLUMN_TITULO + "=?";
        String[] selectionArgs = {searchItem};
        String limit = "1";

        Cursor cursor = mDB.query(FavoriteContrat.FavoriteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;

    }

    public void configurarAdapter() {
        trailerList = new ArrayList<>();
        adaptertrailer = new TrailerAdapter(trailerList, this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adaptertrailer);
    }

    @Override
    public void mostrarTrailer(List<Trailer> trailers) {
        adaptertrailer.setTrailers(trailers);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de Trailers.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destruirView();
    }

    public void saveFavorite() {
        favoriteDBHelper = new FavoriteDBHelper(activity);
        favoriteDBHelper.addFavorite(filme);
    }

}