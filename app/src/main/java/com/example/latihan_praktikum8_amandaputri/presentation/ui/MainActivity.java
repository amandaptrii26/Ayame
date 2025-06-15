package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum8_amandaputri.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.navigation_konten) {
                selectedFragment = new KontenFragment();
            } else if (id == R.id.navigation_favorit) {
                selectedFragment = new FavoritFragment();
            } else if (id == R.id.navigation_setting) {
                selectedFragment = new SettingFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });

        // Fragment default pas pertama buka app
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

}
