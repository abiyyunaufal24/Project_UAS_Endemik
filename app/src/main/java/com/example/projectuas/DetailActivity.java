package com.example.projectuas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projectuas.database.AppDatabase;
import com.example.projectuas.database.Favorit;

import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        String id = getIntent().getStringExtra("ENDEMIK_ID");
        String name = getIntent().getStringExtra("NAME");
        String desc = getIntent().getStringExtra("DESC");
        String img = getIntent().getStringExtra("IMG");

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvDesc = findViewById(R.id.tv_detail_desc);
        ImageView btnFav = findViewById(R.id.btn_favorite_detail);
        ImageView imgDetail = findViewById(R.id.img_detail);

        tvTitle.setText(name);
        tvDesc.setText(desc);
        com.bumptech.glide.Glide.with(this).load(img).into(imgDetail);

        AppDatabase db = AppDatabase.getInstance(this);

        db.endemikDao().isFavorite(id).observe(this, favorite -> {
            isFav = favorite != null && favorite;
            if (isFav) {
                btnFav.setColorFilter(getResources().getColor(android.R.color.holo_red_dark));
            } else {
                btnFav.setColorFilter(getResources().getColor(android.R.color.black));
            }
        });

        btnFav.setOnClickListener(v -> {
            if (isFav) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    db.endemikDao().deleteFavorite(new Favorit(id));
                });
            } else {
                Executors.newSingleThreadExecutor().execute(() -> {
                    db.endemikDao().insertFavorite(new Favorit(id));
                });
                showFavoriteDialog();
            }
        });
    }

    private void showFavoriteDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Ketika kamu klik tombol ini, maka data tersimpan dalam Favorit")
                .setPositiveButton("Tutup", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
