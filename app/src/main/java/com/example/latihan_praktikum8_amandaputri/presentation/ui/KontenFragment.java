package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_praktikum8_amandaputri.R;
import com.example.latihan_praktikum8_amandaputri.presentation.adapter.KontenAdapter;
import com.example.latihan_praktikum8_amandaputri.presentation.viewmodel.KontenViewModel;

import java.util.ArrayList;

public class KontenFragment extends Fragment {

    private KontenAdapter kontenAdapter;
    private KontenViewModel kontenViewModel;
    private EditText searchEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konten, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewKonten);
        searchEditText = view.findViewById(R.id.searchEditText);

        kontenAdapter = new KontenAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(kontenAdapter);

        kontenViewModel = new ViewModelProvider(this).get(KontenViewModel.class);

        kontenViewModel.getAllKonten().observe(getViewLifecycleOwner(), kontenList -> {
            kontenAdapter.setData(kontenList);
        });

        setupSearchBar();

        return view;
    }

    private void setupSearchBar() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchKonten(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void searchKonten(String keyword) {
        if (keyword.isEmpty()) {
            kontenViewModel.getAllKonten().observe(getViewLifecycleOwner(), kontenList -> {
                kontenAdapter.setData(kontenList);
            });
        } else {
            kontenViewModel.searchKonten(keyword).observe(getViewLifecycleOwner(), kontenList -> {
                kontenAdapter.setData(kontenList);
            });
        }
    }
}
