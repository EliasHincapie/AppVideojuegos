package com.example.appvideojuegos.Presentador;

import android.content.Context;
import android.util.Log;

import com.example.appvideojuegos.Modelo.FavoritesManagerImpl;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Config.ApiService;
import com.example.appvideojuegos.Config.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter  {
    private MainView vista;
    private ApiService apiService;
    private FavoritesManager favoritesManager;


    public MainPresenterImpl(MainView view) {
        this.vista = view;
        apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        // Inicializar correctamente el FavoritesManager
        if (view instanceof Context) {
            this.favoritesManager = new FavoritesManagerImpl((Context) view);
        }
    }

    @Override
    public void obtenerJuegos() {
        vista.mostrarCargando();
        apiService.getGames().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                vista.ocultarCargando();
                if (response.isSuccessful() && response.body() != null) {
                    vista.mostrarJuegos(response.body());
                } else {
                    vista.mostrarError("Error al obtener juegos");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.e("MainPresenter", "Error al obtener juegos", t);

                vista.ocultarCargando();
                vista.mostrarError("Fallo de conexión: " + t.getMessage());
            }
        });
    }
    public void agregarAFavoritos(Game game) {
        favoritesManager.addFavorite(game);
        vista.actualizarListaFavoritos(favoritesManager.getAllFavorites());
    }

    public void eliminarDeFavoritos(int gameId) {
        if (favoritesManager != null) {
            favoritesManager.removeFavorite(gameId);
            vista.actualizarListaFavoritos(favoritesManager.getAllFavorites());
        }
    }

    public boolean esFavorito(int gameId) {
        return favoritesManager.isFavorite(gameId);
    }

    public List<Game> obtenerFavoritos() {
        return favoritesManager != null ? favoritesManager.getAllFavorites() : null;
    }
}



