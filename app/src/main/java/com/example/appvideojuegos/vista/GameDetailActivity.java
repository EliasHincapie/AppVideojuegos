package com.example.appvideojuegos.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.appvideojuegos.Modelo.FavoritesManagerImpl;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.R;

public class GameDetailActivity extends AppCompatActivity {
    private ImageView gameImage, favoriteIcon;
    private TextView gameTitle,gameId, gameShortDescription;
    private boolean isFavorite;
    private FavoritesManager favoritesManager;

    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Inicializar vistas
        gameImage = findViewById(R.id.gameImageDetail);
        favoriteIcon = findViewById(R.id.favoriteIcon);

        gameId = findViewById(R.id.gameIdDetail);
        gameTitle = findViewById(R.id.gameTitleDetail);


        gameShortDescription =   findViewById(R.id.gameShortDescription);

        // Inicializar FavoritesManager
        favoritesManager = new FavoritesManagerImpl(this);


        // Recibir datos del Intent
        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game_object");

        if (currentGame != null) {
         // Mostrar los detalles del juego
            gameTitle.setText(currentGame.getTitle() != null ? currentGame.getTitle() : "Título no disponible");
            gameId.setText(String.valueOf("ID: "+ currentGame.getId()));


           // gameShortDescription.setText(String.valueOf(currentGame.getShortDescription() !=null ? currentGame.getShortDescription() : "descripcion no disponible"));
            gameShortDescription.setText(String.valueOf("DESCRIPTION: " + currentGame.getShortDescription()));



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
