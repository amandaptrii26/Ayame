package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.latihan_praktikum8_amandaputri.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {

    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;

    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Button btnLogout = view.findViewById(R.id.btnLogout);
        ImageView ivModeGelap = view.findViewById(R.id.ivModeGelap);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        // Logout
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent intent = new Intent(requireActivity(), FragmentLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                requireActivity().finish();
            });
        });

        // Tombol Tema
        ivModeGelap.setOnClickListener(v -> {
            SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            boolean isDarkMode = prefs.getBoolean(KEY_DARK_MODE, false);

            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            prefs.edit().putBoolean(KEY_DARK_MODE, !isDarkMode).apply();
            requireActivity().recreate();
        });

        return view;
    }
}
