package com.example.latihan_praktikum8_amandaputri.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.latihan_praktikum8_amandaputri.data.dao.MovieDao;
import com.example.latihan_praktikum8_amandaputri.data.database.AppDatabase;
import com.example.latihan_praktikum8_amandaputri.data.entity.MovieEntity;

import java.util.List;

public class MovieRepository {

    private final MovieDao movieDao;

    public MovieRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "movie_database")
                .allowMainThreadQueries()
                .build();
        movieDao = db.movieDao();
    }

    public List<MovieEntity> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public List<MovieEntity> searchMovies(String query) {
        return movieDao.searchMovies(query);
    }

    public void insertMovie(MovieEntity movie) {
        movieDao.insert(movie);
    }
}
