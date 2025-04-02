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
    // Nombre del archivo de preferencias compartidas
    private static final String PREFS_NAME = "favorites_prefs";
    // Clave para almacenar la lista de favoritos en SharedPreferences
    private static final String FAVORITES_KEY = "favorites";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    // Constructor que inicializa SharedPreferences y Gson
    public FavoritesManagerImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    @Override
    public void addFavorite(Game game) {
        List<Game> favorites = getAllFavorites(); // Obtiene la lista de juegos favoritos
        if (!isFavorite(game.getId())) { // Verifica si el juego no está en la lista
            favorites.add(game); // Agrega el juego a la lista
            saveFavorites(favorites); // Guarda la lista actualizada en SharedPreferences
        }
    }

    @Override
    public void removeFavorite(int gameId) {
        List<Game> favorites = getAllFavorites(); // Obtiene la lista de favoritos
        for (Game game : favorites) {
            if (game.getId() == gameId) { // Encuentra el juego por su ID
                favorites.remove(game); // Lo elimina de la lista
                break;
            }
        }
        saveFavorites(favorites); // Guarda la lista actualizada en SharedPreferences
    }

    @Override
    public boolean isFavorite(int gameId) {
        List<Game> favorites = getAllFavorites(); // Obtiene la lista de favoritos
        for (Game game : favorites) {
            if (game.getId() == gameId) {
                return true; // Devuelve true si el juego está en la lista de favoritos
            }
        }
        return false; // Devuelve false si el juego no está en la lista
    }

    @Override
    public List<Game> getAllFavorites() {
        String json = sharedPreferences.getString(FAVORITES_KEY, null); // Obtiene la lista guardada en formato JSON
        Type type = new TypeToken<ArrayList<Game>>() {}.getType(); // Define el tipo de la lista
        List<Game> favorites = gson.fromJson(json, type); // Convierte el JSON a una lista de juegos
        return favorites != null ? favorites : new ArrayList<>(); // Retorna la lista o una vacía si no hay favoritos
    }

    // Metodo para guardar la lista de favoritos en SharedPreferences
    private void saveFavorites(List<Game> favorites) {
        String json = gson.toJson(favorites); // Convierte la lista a formato JSON
        sharedPreferences.edit().putString(FAVORITES_KEY, json).apply(); // Guarda la lista en SharedPreferences
    }
}
