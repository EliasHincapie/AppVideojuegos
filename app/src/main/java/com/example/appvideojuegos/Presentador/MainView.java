package com.example.appvideojuegos.Presentador;

import com.example.appvideojuegos.Modelo.Game;
import java.util.List;

public interface MainView {
    void showGames(List<Game> games);
    void showError(String message);
}