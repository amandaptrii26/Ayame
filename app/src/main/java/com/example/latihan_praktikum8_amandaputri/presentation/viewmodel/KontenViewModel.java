package com.example.latihan_praktikum8_amandaputri.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.latihan_praktikum8_amandaputri.data.entity.Konten;
import com.example.latihan_praktikum8_amandaputri.data.repository.KontenRepository;

import java.util.List;

public class KontenViewModel extends AndroidViewModel {

    private final KontenRepository repository;
    private final LiveData<List<Konten>> allKonten;

    public KontenViewModel(@NonNull Application application) {
        super(application);
        repository = new KontenRepository(application);
        allKonten = repository.getAllKonten();

        // Cek, kalau database kosong, isi data dummy
        allKonten.observeForever(kontenList -> {
            if (kontenList == null || kontenList.isEmpty()) {
                insertDummyData();
            }
        });
    }

    public LiveData<List<Konten>> getAllKonten() {
        return allKonten;
    }

    public LiveData<List<Konten>> searchKonten(String keyword) {
        return repository.searchKonten("%" + keyword + "%");
    }

    public void insert(Konten konten) {
        repository.insert(konten);
    }

    private void insertDummyData() {
        insert(new Konten("Doraemon The Movie"));
        insert(new Konten("Nussa The Movie"));
        insert(new Konten("Moana"));
        insert(new Konten("Encanto"));
        insert(new Konten("Wish Dragon"));
        insert(new Konten("Despicable Me"));
        insert(new Konten("Zootopia"));
        insert(new Konten("Spirited Away"));
        insert(new Konten("Elemental"));
        insert(new Konten("The Boss Baby"));

    }
}
