package com.example.latihan_praktikum8_amandaputri.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.latihan_praktikum8_amandaputri.data.entity.MovieEntity;
import com.example.latihan_praktikum8_amandaputri.data.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        loadMovies();
    }

    public void loadMovies() {
        movies.setValue(movieRepository.getAllMovies());
    }

    public LiveData<List<MovieEntity>> getMovies() {
        return movies;
    }

    public void searchMovies(String query) {
        movies.setValue(movieRepository.searchMovies(query));
    }
}
