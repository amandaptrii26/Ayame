package com.example.latihan_praktikum8_amandaputri.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.latihan_praktikum8_amandaputri.data.entity.Konten;

import java.util.List;

@Dao
public interface KontenDao {

    @Insert
    void insert(Konten konten);

    @Query("SELECT * FROM konten")
    LiveData<List<Konten>> getAllKonten();

    @Query("SELECT * FROM konten WHERE title LIKE '%' || :keyword || '%'")
    LiveData<List<Konten>> searchKonten(String keyword);
}
