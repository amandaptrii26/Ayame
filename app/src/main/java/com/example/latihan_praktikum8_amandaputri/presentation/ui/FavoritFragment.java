package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum8_amandaputri.R;

public class FavoritFragment extends Fragment {

    public FavoritFragment() {
        // Konstruktor kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorit, container, false);
    }
}
