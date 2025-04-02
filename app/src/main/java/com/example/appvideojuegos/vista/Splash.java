package com.example.appvideojuegos.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appvideojuegos.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia la actividad principal
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad de splash para que no vuelva al presionar "Atrás"
            }
        }, SPLASH_DURATION);
    }
}