package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        Button btnUnderstand = findViewById(R.id.btn_understand);
        btnUnderstand.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity.this, SplashActivity.class));
            finish();
        });
    }
}
