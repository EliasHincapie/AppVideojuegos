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

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFavorites;
    private GameAdapter gameAdapter;
    private List<Game> favoriteGames;
    private FavoritesManager favoritesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new GridLayoutManager(this, 2));

        // Usar FavoritesManager para mantener consistencia
        favoritesManager = new FavoritesManagerImpl(this);
        favoriteGames = favoritesManager.getAllFavorites();

        gameAdapter = new GameAdapter(this, favoriteGames, new OnItemClickListener() {
            @Override
            public void onItemClick(Game game) {
                Intent intent = new Intent(FavoritesActivity.this, GameDetailActivity.class);
                intent.putExtra("game_object", game);
                startActivity(intent);
            }
        });

        recyclerViewFavorites.setAdapter(gameAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar la lista de favoritos cuando se regresa a esta actividad
        favoriteGames = favoritesManager.getAllFavorites();
        gameAdapter = new GameAdapter(this, favoriteGames, game -> {
            Intent intent = new Intent(FavoritesActivity.this, GameDetailActivity.class);
            intent.putExtra("game_object", game);
            startActivity(intent);
        });
        recyclerViewFavorites.setAdapter(gameAdapter);
    }
}