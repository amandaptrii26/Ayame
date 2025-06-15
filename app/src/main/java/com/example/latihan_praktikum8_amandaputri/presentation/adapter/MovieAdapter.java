package com.example.latihan_praktikum8_amandaputri.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_praktikum8_amandaputri.R;
import com.example.latihan_praktikum8_amandaputri.data.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieEntity> movieList = new ArrayList<>();

    public void setMovies(List<MovieEntity> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movieList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvDescription.setText(movie.getDescription());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
