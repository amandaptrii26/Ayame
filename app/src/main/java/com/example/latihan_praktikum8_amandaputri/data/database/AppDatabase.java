package com.example.latihan_praktikum8_amandaputri.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.latihan_praktikum8_amandaputri.data.dao.KontenDao;
import com.example.latihan_praktikum8_amandaputri.data.dao.MovieDao;
import com.example.latihan_praktikum8_amandaputri.data.entity.Konten;
import com.example.latihan_praktikum8_amandaputri.data.entity.MovieEntity;

@Database(entities = {MovieEntity.class, Konten.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract KontenDao kontenDao();
}
