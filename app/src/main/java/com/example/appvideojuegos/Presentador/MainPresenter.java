package com.example.appvideojuegos.Presentador;


import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Config.ApiService;
import com.example.appvideojuegos.Config.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class MainPresenter {
        private MainView view;
        private ApiService apiService;

        public MainPresenter(MainView view) {
            this.view = view;
            apiService = RetrofitClient.getClient().create(ApiService.class);
        }

        public void loadGames() {
            apiService.getGames().enqueue(new Callback<List<Game>>() {
                @Override
                public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        view.showGames(response.body());
                    } else {
                        view.showError("Error al obtener los datos");
                    }
                }

                @Override
                public void onFailure(Call<List<Game>> call, Throwable t) {
                    view.showError("Error de conexión: " + t.getMessage());
                }
            });
        }
    }


