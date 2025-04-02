package com.example.appvideojuegos.Config;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.appvideojuegos.Modelo.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameApiService {
    // URL base de la API de juegos
    private static final String BASE_URL = "https://www.freetogame.com/api/";

    // Contexto de la aplicación, necesario para realizar solicitudes con Volley
    private Context context;

    // Instancia de Gson para convertir JSON en objetos Java
    private Gson gson;

    // Interfaz para manejar la respuesta de la API
    public interface GamesResponseListener {
        void onResponse(List<Game> games); // Metodo llamado cuando la API responde con éxito
        void onError(String error); // Metodo llamado cuando ocurre un error
    }

    // Constructor de la clase, recibe el contexto de la aplicación
    public GameApiService(Context context) {
        this.context = context;
        this.gson = new Gson(); // Inicializa la instancia de Gson
    }

    // Metodo para obtener la lista de juegos desde la API
    public void getGames(final GamesResponseListener listener) {
        // Construcción de la URL completa
        String url = BASE_URL + "games";

        // Creación de una solicitud JSON de tipo array a la API
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Convertir el JSONArray a una cadena JSON
                            String jsonString = response.toString();

                            // Definir el tipo de datos esperado para Gson (Lista de objetos Game)
                            Type gameListType = new TypeToken<ArrayList<Game>>(){}.getType();

                            // Convertir la cadena JSON en una lista de objetos Game usando Gson
                            List<Game> games = gson.fromJson(jsonString, gameListType);

                            // Pasar la lista de juegos a través del listener
                            listener.onResponse(games);
                        } catch (Exception e) {
                            // Manejo de errores en la conversión de JSON
                            listener.onError("Error parsing data: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejo de errores de red
                        listener.onError("Network error: " + (error.getMessage() != null ? error.getMessage() : "Unknown error"));
                    }
                });

        // Agregar la solicitud a la cola de Volley para su ejecución
        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }
}
