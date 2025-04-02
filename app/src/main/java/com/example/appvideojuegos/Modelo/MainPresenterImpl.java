package com.example.appvideojuegos.Modelo;

import android.content.Context;
import android.util.Log;

import com.example.appvideojuegos.Config.GameApiService;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.Presentador.MainPresenter;
import com.example.appvideojuegos.Presentador.MainView;

import java.util.List;

// Implementación del presentador principal que maneja la lógica de obtención de datos desde la API
public class MainPresenterImpl implements MainPresenter {
    private MainView vista; // Vista asociada al presentador
    private GameApiService apiService; // Servicio para obtener juegos desde la API
    private FavoritesManager favoritesManager; // Administrador de favoritos

    // Constructor del presentador
    public MainPresenterImpl(MainView view) {
        this.vista = view;

        // Inicializa correctamente FavoritesManager y GameApiService si la vista es un contexto válido
        if (view instanceof Context) {
            Context context = (Context) view;
            this.favoritesManager = new FavoritesManagerImpl(context);
            this.apiService = new GameApiService(context);
        }
    }

    // Metodo para obtener la lista de juegos desde la API
    @Override
    public void obtenerJuegos() {
        vista.mostrarCargando(); // Indica a la vista que se está cargando la información

        // Realiza la petición a la API
        apiService.getGames(new GameApiService.GamesResponseListener() {
            @Override
            public void onResponse(List<Game> games) {
                vista.ocultarCargando(); // Oculta la indicación de carga en la vista

                // Registro en Log para depuración
                if (!games.isEmpty()) {
                    Game firstGame = games.get(0);
                    Log.d("API_RESPONSE", "First game: " + "Title: " + firstGame.getTitle() +
                            ", Description: " + firstGame.getShortDescription());
                }

                // Envía la lista de juegos obtenidos a la vista
                vista.mostrarJuegos(games);
            }

            @Override
            public void onError(String error) {
                Log.e("MainPresenter", "Error al obtener juegos: " + error); // Registra el error en Logcat
                vista.ocultarCargando(); // Oculta la carga en la vista
                vista.mostrarError("Fallo de conexión: " + error); // Muestra un mensaje de error a la vista
            }
        });
    }
}