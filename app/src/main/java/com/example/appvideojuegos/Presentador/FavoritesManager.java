package com.example.appvideojuegos.Presentador;

import com.example.appvideojuegos.Modelo.Game;

import java.util.List;

public interface FavoritesManager {
    void addFavorite(Game game);
    void removeFavorite(int gameId);
    boolean isFavorite(int gameId);
    List<Game> getAllFavorites();

}
