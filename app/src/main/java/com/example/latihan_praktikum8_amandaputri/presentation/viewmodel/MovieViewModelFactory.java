package com.example.latihan_praktikum8_amandaputri.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.latihan_praktikum8_amandaputri.data.repository.MovieRepository;

public class MovieViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    public MovieViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
