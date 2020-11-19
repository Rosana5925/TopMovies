package com.example.topmovies.UI.detalhesFilmes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.R;
import com.example.topmovies.entidades.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private static List<Review> reviews;
    private static Context mContext;

    public ReviewAdapter(List<Review> reviews, Context mContext) {
        this.reviews = reviews;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        holder.autor.setText(reviews.get(position).getAutor());
        holder.contexto.setText(reviews.get(position).getConteudo());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView autor;
        public TextView contexto;


        public ViewHolder(View itemView) {
            super(itemView);
            autor = (TextView) itemView.findViewById(R.id.autor_review);
            contexto = (TextView) itemView.findViewById(R.id.contexto_review);


        }
    }
    public void setReviews(List<Review> reviews){
        this.reviews=reviews;
        notifyDataSetChanged();
    }
}
