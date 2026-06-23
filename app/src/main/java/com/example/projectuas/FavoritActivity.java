package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.database.AppDatabase;

public class FavoritActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView rvFavorit = findViewById(R.id.rv_favorit);
        rvFavorit.setLayoutManager(new GridLayoutManager(this, 2));
        EndemikAdapter adapter = new EndemikAdapter();
        rvFavorit.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", endemik.id);
            intent.putExtra("NAME", endemik.name);
            intent.putExtra("DESC", endemik.description);
            intent.putExtra("IMG", endemik.imageRes);
            startActivity(intent);
        });

        AppDatabase.getInstance(this).endemikDao().getFavoriteEndemik().observe(this, list -> {
            adapter.setList(list);
        });
    }
}
