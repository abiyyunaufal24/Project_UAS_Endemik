package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectuas.api.ApiClient;
import com.example.projectuas.database.AppDatabase;
import com.example.projectuas.database.Endemik;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fetchDataFromApi();

        Button btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        });
    }

    private void fetchDataFromApi() {
        ApiClient.getApiService().getEndemikData().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveToDatabase(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                // Handle error
            }
        });
    }

    private void saveToDatabase(List<Endemik> data) {
        AppDatabase db = AppDatabase.getInstance(this);
        Executors.newSingleThreadExecutor().execute(() -> {
            db.endemikDao().insertEndemik(data.toArray(new Endemik[0]));
        });
    }
}
