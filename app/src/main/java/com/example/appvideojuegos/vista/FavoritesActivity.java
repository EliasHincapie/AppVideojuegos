package com.example.appvideojuegos.vista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appvideojuegos.Adapter.GameAdapter;
import com.example.appvideojuegos.Adapter.OnItemClickListener;
import com.example.appvideojuegos.Modelo.FavoritesManagerImpl;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.R;

import java.util.List;

/**
 * Actividad que muestra la lista de juegos marcados como favoritos.
 */
public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFavorites; // RecyclerView para mostrar los juegos favoritos
    private GameAdapter gameAdapter; // Adaptador para la lista de juegos
    private List<Game> favoriteGames; // Lista de juegos favoritos
    private FavoritesManager favoritesManager; // Gestor de favoritos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Inicializar RecyclerView
        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new GridLayoutManager(this, 2)); // Mostrar en un grid de 2 columnas

        // Inicializar el gestor de favoritos
        favoritesManager = new FavoritesManagerImpl(this);
        favoriteGames = favoritesManager.getAllFavorites(); // Obtener la lista de favoritos

        // Crear el adaptador con la lista de favoritos
        gameAdapter = new GameAdapter(this, favoriteGames, new OnItemClickListener() {
            @Override
            public void onItemClick(Game game) {
                // Al hacer clic en un juego, abrir su detalle
                Intent intent = new Intent(FavoritesActivity.this, GameDetailActivity.class);
                intent.putExtra("game_object", game);
                startActivity(intent);
            }
        });

        // Asignar el adaptador al RecyclerView
        recyclerViewFavorites.setAdapter(gameAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Al regresar a la actividad, actualizar la lista de favoritos
        favoriteGames = favoritesManager.getAllFavorites();

        // Crear nuevamente el adaptador con la lista actualizada
        gameAdapter = new GameAdapter(this, favoriteGames, game -> {
            Intent intent = new Intent(FavoritesActivity.this, GameDetailActivity.class);
            intent.putExtra("game_object", game);
            startActivity(intent);
        });

        // Asignar el nuevo adaptador al RecyclerView para reflejar cambios
        recyclerViewFavorites.setAdapter(gameAdapter);
    }
}
