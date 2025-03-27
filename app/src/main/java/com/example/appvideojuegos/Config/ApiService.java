package com.example.appvideojuegos.Config;

import com.example.appvideojuegos.Modelo.Game;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("games")
    Call<List<Game>> getGames(); // Llama a la API y devuelve una lista de juegos
}