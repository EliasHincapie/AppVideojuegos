package com.example.appvideojuegos.vista;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.appvideojuegos.Modelo.FavoritesManagerImpl;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameDetailActivity extends AppCompatActivity {
    private ImageView gameImage, favoriteIcon;
    private TextView gameTitle, gameDescription;
    private boolean isFavorite = false;
    private FavoritesManager favoritesManager;

    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Inicializar vistas
        gameImage = findViewById(R.id.gameImageDetail);
        favoriteIcon = findViewById(R.id.favoriteIcon);
        gameTitle = findViewById(R.id.gameTitleDetail);
        gameDescription = findViewById(R.id.gameDescriptionDetail);

        // Inicializar FavoritesManager
        favoritesManager = new FavoritesManagerImpl(this);


        // Recibir datos del Intent
        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game_object");

        if (currentGame != null) {
            int gameId = intent.getIntExtra("game_id", -1);
            if (gameId != -1) {
                // Aquí deberías buscar el juego por ID en tu lista o realizar una llamada a la API
                // Por ahora, mostraremos un mensaje de error
                gameTitle.setText("Error: No se pudo cargar el juego");
                return;
            }
        }

        if (currentGame != null) {
            gameTitle.setText(currentGame.getTitle() != null ? currentGame.getTitle() : "Título no disponible");
            gameDescription.setText(currentGame.getShortDescription() != null ? currentGame.getShortDescription() : "Descripción no disponible");

            if (currentGame.getThumbnail() != null && !currentGame.getThumbnail().isEmpty()) {
                Glide.with(this).load(currentGame.getThumbnail()).into(gameImage);
            } else {
                gameImage.setImageResource(R.drawable.imagen); // Imagen por defecto
            }

            // Verificar si el juego es favorito
            isFavorite = favoritesManager.isFavorite(currentGame.getId());
            updateFavoriteIcon();


            // Manejar clic en el ícono de favorito
            favoriteIcon.setOnClickListener(v -> {
                if (isFavorite) {
                    favoritesManager.removeFavorite(currentGame.getId());
                } else {
                    favoritesManager.addFavorite(currentGame);
                }
                isFavorite = !isFavorite;
                updateFavoriteIcon();
            });
        } else {
            // Manejo en caso de que no se reciban datos
            finish(); // Cierra la actividad si no hay datos
        }
    }


    private void updateFavoriteIcon() {
        if (isFavorite) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_border);
        }
        favoriteIcon.invalidate(); //  Forzar redibujado del icono
    }

}

