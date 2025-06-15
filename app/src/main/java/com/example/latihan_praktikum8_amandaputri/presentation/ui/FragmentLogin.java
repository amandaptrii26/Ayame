package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.latihan_praktikum8_amandaputri.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class FragmentLogin extends AppCompatActivity {

    private ImageView googleLoginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 123;
    private GoogleApiClient mGoogleApiClient;

    private EditText etEmail, etPassword;
    private Button btnEmailLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        // UI
        googleLoginBtn = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnEmailLogin = findViewById(R.id.btnEmailLogin);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Konfigurasi Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // API Google
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Koneksi Google gagal!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // tombol Google Login (gambar nih)
        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAkunGoogle();
            }
        });

        // tombol login email lawan password
        btnEmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDenganEmailPassword();
            }
        });
    }

    // Proses login pakai akun Google
    private void loginAkunGoogle() {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    // Proses login pakai Email dan Password
    private void loginDenganEmailPassword() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(FragmentLogin.this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(FragmentLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(FragmentLogin.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(FragmentLogin.this, "Selamat Datang " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FragmentLogin.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // sagan login dari Google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null && result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Login Google gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Autentikasi Google ke Firebase
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(FragmentLogin.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Selamat Datang " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Autentikasi Google gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
