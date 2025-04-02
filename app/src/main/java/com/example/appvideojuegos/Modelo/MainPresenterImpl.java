package com.example.appvideojuegos.Modelo;

import android.content.Context;
import android.util.Log;

import com.example.appvideojuegos.Config.ApiService;
import com.example.appvideojuegos.Config.RetrofitClient;
import com.example.appvideojuegos.Presentador.FavoritesManager;
import com.example.appvideojuegos.Presentador.MainPresenter;
import com.example.appvideojuegos.Presentador.MainView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter {
    private MainView vista;
    private ApiService apiService;
    private FavoritesManager favoritesManager;


    public MainPresenterImpl(MainView view) {
        this.vista = view;
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
                    // Añade este log para ver los datos de la respuesta
                    if (!response.body().isEmpty()) {
                        Game firstGame = response.body().get(0);
                        Log.d("API_RESPONSE", "First game: " +
                                "Title: " + firstGame.getTitle() +
                                ", Description: " + firstGame.getShortDescription());
                    }
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
}

