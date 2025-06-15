package com.example.latihan_praktikum8_amandaputri.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.latihan_praktikum8_amandaputri.data.dao.KontenDao;
import com.example.latihan_praktikum8_amandaputri.data.database.AppDatabase;
import com.example.latihan_praktikum8_amandaputri.data.entity.Konten;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KontenRepository {

    private final KontenDao kontenDao;
    private final ExecutorService executorService;

    public KontenRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(
                application.getApplicationContext(),
                AppDatabase.class,
                "db_amanda"
        ).fallbackToDestructiveMigration().build();

        kontenDao = db.kontenDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Konten>> getAllKonten() {
        return kontenDao.getAllKonten();
    }

    public LiveData<List<Konten>> searchKonten(String keyword) {
        return kontenDao.searchKonten(keyword);
    }

    public void insert(Konten konten) {
        executorService.execute(() -> kontenDao.insert(konten));
    }
}
