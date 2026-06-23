package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.nav_hewan) {
                selectedFragment = new HewanFragment();
            } else if (item.getItemId() == R.id.nav_tumbuhan) {
                selectedFragment = new TumbuhanFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HewanFragment())
                    .commit();
            showWelcomeDialog();
        }

        ImageView btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PencarianActivity.class)));

        ImageView btnFavorite = findViewById(R.id.btn_favorite);
        btnFavorite.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, FavoritActivity.class)));
    }

    private void showWelcomeDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Selamat Datang, Abiyyu")
                .setMessage("Ini Adalah Home Activity yang memiliki 2 Fragment.\n\nHewanFragment menampilkan data yang berkategori \"Hewan\".\n\nSedangkan, TumbuhanFragment menampilkan data yang memiliki kategori \"Tumbuhan\"")
                .setPositiveButton("Tutup", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
