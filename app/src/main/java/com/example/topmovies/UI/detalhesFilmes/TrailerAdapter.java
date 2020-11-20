package com.example.topmovies.UI.detalhesFilmes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.R;
import com.example.topmovies.UI.MyRecyclerViewAdapter;
import com.example.topmovies.entidades.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private static List<Trailer> trailers;
    private static Context mContext;

    public TrailerAdapter(List<Trailer> trailers, Context mContext) {
        this.trailers = trailers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_trailer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.ViewHolder holder, int position) {
        holder.titulo.setText(trailers.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public ImageView imageV;

        public ViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.titulo);
            imageV = (ImageView) view.findViewById(R.id.image_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Trailer clickedDataItem = trailers.get(pos);
                        String videoId = trailers.get(pos).getChave();

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO", videoId);
                        mContext.startActivity(intent);

                        Toast.makeText(v.getContext(), "you clicked" + clickedDataItem.getNome(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

}
