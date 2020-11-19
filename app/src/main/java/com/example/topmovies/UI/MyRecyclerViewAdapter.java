package com.example.topmovies.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.R;
import com.example.topmovies.entidades.Filme;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter  extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Filme>filmes;
    private ItemFilmeClickListener mClickListener;

    private static ItemFilmeClickListener ItemFilmeClickListener;

    public  MyRecyclerViewAdapter(ItemFilmeClickListener ItemFilmeClickListener){
        this.ItemFilmeClickListener=ItemFilmeClickListener;
        filmes=new ArrayList<>();
    }


    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView and Imageview in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(filmes.get(position));
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return filmes.size();
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
      private final  TextView myTextViewTitulo;
      private final  TextView myTextViewvotos;
      private final  TextView myTextViewvisaogeral;
      private final  TextView myTextViewlacamento;
       private final ImageView myimage;
       private Filme filme;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewTitulo = itemView.findViewById(R.id.titulo_filme);
            myimage=itemView.findViewById(R.id.poster_filme);
            myTextViewvotos = itemView.findViewById(R.id.votos);
            myTextViewvisaogeral=itemView.findViewById(R.id.textViewVisaoglobal);
            myTextViewlacamento=itemView.findViewById(R.id.textViewlancamento);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ItemFilmeClickListener!=null){
                        ItemFilmeClickListener.onItemFilmeClicado(filme);

                    }
                }
            });


        }

        public void bind(Filme filme){
            this.filme=filme;

            myTextViewTitulo.setText(filme.getTitulo());
            myTextViewvotos.setText(filme.getVoto());
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342"+filme.getCaminhoPoster())
                    .into(myimage);
        }

    }

    public void setFilmes(List<Filme> filmes){
        this.filmes=filmes;
        notifyDataSetChanged();
    }

    public interface ItemFilmeClickListener{
        void onItemFilmeClicado(Filme filme);


    }
}