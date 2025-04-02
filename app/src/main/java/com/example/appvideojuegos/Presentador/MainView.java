package com.example.appvideojuegos.Presentador;

import com.example.appvideojuegos.Modelo.Game;
import java.util.List;

/**
 * Interfaz que define los métodos que la vista (actividad o fragmento) debe implementar.
 * Permite la comunicación entre el Presenter y la Vista en el patrón MVP.
 */
public interface MainView {

    /**
     * Muestra un indicador de carga mientras se obtienen los datos de la API.
     */
    void mostrarCargando();


    /**
     * Oculta el indicador de carga cuando los datos han sido obtenidos.
     */
    void ocultarCargando();


    /**
     * Muestra la lista de juegos obtenida desde la API en la interfaz de usuario.
     * @param juegos Lista de juegos obtenidos.
     */
    void mostrarJuegos(List<Game> juegos);


    /**
     * Muestra un mensaje de error si ocurre algún problema en la obtención de datos.
     * @param mensaje Mensaje de error a mostrar.
     */
    void mostrarError(String mensaje);


    /**
     * Se ejecuta cuando el usuario selecciona un juego en la lista.
     * @param juego Juego seleccionado.
     */
    void onItemClick(Game juego);


    //Actualiza la lista de juegos favoritos en la interfaz de usuario.
    void actualizarListaFavoritos(List<Game> allFavorites);
}
