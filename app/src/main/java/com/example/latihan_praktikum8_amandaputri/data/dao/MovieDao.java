package com.example.latihan_praktikum8_amandaputri.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.latihan_praktikum8_amandaputri.data.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(MovieEntity movie);

    @Query("SELECT * FROM movies")
    List<MovieEntity> getAllMovies();

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    List<MovieEntity> searchMovies(String query);
}
