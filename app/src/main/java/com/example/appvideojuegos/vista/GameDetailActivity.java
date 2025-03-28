package com.example.appvideojuegos.vista;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.appvideojuegos.R;

public class GameDetailActivity extends AppCompatActivity {
    private ImageView gameImage, favoriteIcon;
    private TextView gameTitle, gameDescription;
    private boolean isFavorite = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Inicializar vistas
        gameImage = findViewById(R.id.gameImageDetail);
        favoriteIcon = findViewById(R.id.favoriteIcon);
        gameTitle = findViewById(R.id.gameTitleDetail);
        gameDescription = findViewById(R.id.gameDescriptionDetail);

        // Recibir datos del Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("game_title");
        String imageUrl = intent.getStringExtra("game_image");
        String description = intent.getStringExtra("game_description");

        gameTitle.setText(title);
        gameDescription.setText(description);
        Glide.with(this).load(imageUrl).into(gameImage);

        // Configurar favoritos usando SharedPreferences
        sharedPreferences = getSharedPreferences("favorites", MODE_PRIVATE);
        isFavorite = sharedPreferences.getBoolean(title, false);
        updateFavoriteIcon();

        // Manejar clic en el ícono de favorito
        favoriteIcon.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            sharedPreferences.edit().putBoolean(title, isFavorite).apply();
            updateFavoriteIcon();
        });
    }

    private void updateFavoriteIcon() {
        if (isFavorite) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}
