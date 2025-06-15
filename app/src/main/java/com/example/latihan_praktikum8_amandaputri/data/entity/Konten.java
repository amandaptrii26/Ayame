package com.example.latihan_praktikum8_amandaputri.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "konten")
public class Konten {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public Konten(String title) {
        this.title = title;
    }

    // Getter Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
}
