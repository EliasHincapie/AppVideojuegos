package com.example.appvideojuegos.Presentador;

import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Config.ApiService;
import com.example.appvideojuegos.Config.RetrofitClient;
import com.example.appvideojuegos.Presentador.MainView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter  {
    private MainView vista;
    private ApiService apiService;

    public MainPresenterImpl(MainView view) {
        this.vista = view;
        apiService = RetrofitClient.getClient().create(ApiService.class);
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
                vista.ocultarCargando();
                vista.mostrarError("Fallo de conexión: " + t.getMessage());
            }
        });
    }

}

