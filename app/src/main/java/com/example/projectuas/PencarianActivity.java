package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.database.AppDatabase;

public class PencarianActivity extends AppCompatActivity {

    private EndemikAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        EditText etSearch = findViewById(R.id.et_search);
        RecyclerView rvSearch = findViewById(R.id.rv_search);

        rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new EndemikAdapter();
        rvSearch.setAdapter(adapter);

        adapter.setOnItemClickListener(endemik -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", endemik.id);
            intent.putExtra("NAME", endemik.name);
            intent.putExtra("DESC", endemik.description);
            intent.putExtra("IMG", endemik.imageRes);
            startActivity(intent);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void search(String query) {
        AppDatabase.getInstance(this).endemikDao().searchEndemik(query).observe(this, list -> {
            adapter.setList(list);
        });
    }
}
