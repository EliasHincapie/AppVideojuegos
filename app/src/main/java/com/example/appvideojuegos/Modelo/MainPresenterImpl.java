package com.example.appvideojuegos.Modelo;

import android.content.Context;
import android.util.Log;

import com.example.appvideojuegos.Config.GameApiService;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.Presentador.MainPresenter;
import com.example.appvideojuegos.Presentador.MainView;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private MainView vista;
    private GameApiService apiService;
    private FavoritesManager favoritesManager;

    public MainPresenterImpl(MainView view) {
        this.vista = view;

        // Inicializar correctamente el FavoritesManager y GameApiService
        if (view instanceof Context) {
            Context context = (Context) view;
            this.favoritesManager = new FavoritesManagerImpl(context);
            this.apiService = new GameApiService(context);
        }
    }

    @Override
    public void obtenerJuegos() {
        vista.mostrarCargando();

        apiService.getGames(new GameApiService.GamesResponseListener() {
            @Override
            public void onResponse(List<Game> games) {
                vista.ocultarCargando();

                // Log para debug
                if (!games.isEmpty()) {
                    Game firstGame = games.get(0);
                    Log.d("API_RESPONSE", "First game: " + "Title: " + firstGame.getTitle() +
                            ", Description: " + firstGame.getShortDescription());
                }

                vista.mostrarJuegos(games);
            }

            @Override
            public void onError(String error) {
                Log.e("MainPresenter", "Error al obtener juegos: " + error);
                vista.ocultarCargando();
                vista.mostrarError("Fallo de conexión: " + error);
            }
        });
    }
}