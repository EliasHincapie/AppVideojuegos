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

//Actividad que muestra los detalles de un juego y permite marcarlo como favorito.
public class GameDetailActivity extends AppCompatActivity {
    private ImageView gameImage, favoriteIcon; // Imagen del juego y botón de favoritos
    private TextView gameTitle, gameId, gameShortDescription; // Texto con la información del juego
    private boolean isFavorite; // Indica si el juego es favorito
    private FavoritesManager favoritesManager; // Administrador de favoritos

    private Game currentGame; // Juego actual mostrado en la pantalla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Inicializar vistas de la interfaz
        gameImage = findViewById(R.id.gameImageDetail);
        favoriteIcon = findViewById(R.id.favoriteIcon);
        gameId = findViewById(R.id.gameIdDetail);
        gameTitle = findViewById(R.id.gameTitleDetail);
        gameShortDescription = findViewById(R.id.gameShortDescription);

        // Inicializar el gestor de favoritos
        favoritesManager = new FavoritesManagerImpl(this);

        // Obtener los datos del juego enviados desde otra actividad
        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game_object");

        if (currentGame != null) {
            // Mostrar los detalles del juego en la interfaz
            gameTitle.setText(currentGame.getTitle() != null ? currentGame.getTitle() : "Título no disponible");
            gameId.setText(String.valueOf("ID: " + currentGame.getId()));
            gameShortDescription.setText("DESCRIPTION: " + (currentGame.getShortDescription() != null ? currentGame.getShortDescription() : "Descripción no disponible"));

            // Cargar imagen del juego usando Glide
            if (currentGame.getThumbnail() != null && !currentGame.getThumbnail().isEmpty()) {
                Glide.with(this).load(currentGame.getThumbnail()).into(gameImage);
            } else {
                gameImage.setImageResource(R.drawable.imagen); // Usar imagen por defecto si no hay miniatura
            }

            // Verificar si el juego ya está marcado como favorito
            isFavorite = favoritesManager.isFavorite(currentGame.getId());
            updateFavoriteIcon(); // Actualizar el ícono de favorito según el estado actual

            // Configurar el evento de clic en el ícono de favoritos
            favoriteIcon.setOnClickListener(v -> {
                if (isFavorite) {
                    favoritesManager.removeFavorite(currentGame.getId()); // Quitar de favoritos
                } else {
                    favoritesManager.addFavorite(currentGame); // Agregar a favoritos
                }
                isFavorite = !isFavorite; // Cambiar el estado
                updateFavoriteIcon(); // Actualizar el ícono
            });
        } else {
            // Si no se reciben datos del juego, cerrar la actividad
            finish();
        }
    }

    //Actualiza el icono de favoritos según el estado actual.
    private void updateFavoriteIcon() {
        if (isFavorite) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_filled); // Ícono de favorito activado
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_border); // Ícono de favorito desactivado
        }
        favoriteIcon.invalidate(); // Forzar la actualización del icono en la interfaz
    }
}
