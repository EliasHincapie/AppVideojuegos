package com.example.appvideojuegos.Presentador;

import com.example.appvideojuegos.Modelo.Game;
import java.util.List;

public interface MainView {
    void mostrarCargando();
    void ocultarCargando();
    void mostrarJuegos(List<Game> juegos);
    void mostrarError(String mensaje);

    void onItemClick(Game juego);

    void actualizarListaFavoritos(List<Game> allFavorites);
}