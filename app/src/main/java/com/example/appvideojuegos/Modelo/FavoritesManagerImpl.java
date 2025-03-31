package com.example.appvideojuegos.Modelo;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoritesManagerImpl implements FavoritesManager {
    private static final String PREFS_NAME = "favorites_prefs";
    private static final String FAVORITES_KEY = "favorites";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public FavoritesManagerImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    @Override
    public void addFavorite(Game game) {
        List<Game> favorites = getAllFavorites();
        if (!isFavorite(game.getId())) {
            favorites.add(game);
            saveFavorites(favorites);
        }
    }

    @Override
    public void removeFavorite(int gameId) {
        List<Game> favorites = getAllFavorites();
        for (Game game : favorites) {
            if (game.getId() == gameId) {
                favorites.remove(game);
                break;
            }
        }
        saveFavorites(favorites);
    }

    @Override
    public boolean isFavorite(int gameId) {
        List<Game> favorites = getAllFavorites();
        for (Game game : favorites) {
            if (game.getId() == gameId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Game> getAllFavorites() {
        String json = sharedPreferences.getString(FAVORITES_KEY, null);
        Type type = new TypeToken<ArrayList<Game>>() {}.getType();
        List<Game> favorites = gson.fromJson(json, type);
        return favorites != null ? favorites : new ArrayList<>();
    }

    private void saveFavorites(List<Game> favorites) {
        String json = gson.toJson(favorites);
        sharedPreferences.edit().putString(FAVORITES_KEY, json).apply();
    }
}
