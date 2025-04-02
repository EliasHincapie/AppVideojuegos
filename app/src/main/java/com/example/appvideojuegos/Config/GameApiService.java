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
    private static final String BASE_URL = "https://www.freetogame.com/api/";
    private Context context;
    private Gson gson;

    public interface GamesResponseListener {
        void onResponse(List<Game> games);
        void onError(String error);
    }

    public GameApiService(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    public void getGames(final GamesResponseListener listener) {
        String url = BASE_URL + "games";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Convert JSONArray to String
                            String jsonString = response.toString();

                            // Use Gson to directly parse the JSON array to List<Game>
                            Type gameListType = new TypeToken<ArrayList<Game>>(){}.getType();
                            List<Game> games = gson.fromJson(jsonString, gameListType);


                            listener.onResponse(games);
                        } catch (Exception e) {
                            listener.onError("Error parsing data: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError("Network error: " + (error.getMessage() != null ? error.getMessage() : "Unknown error"));
                    }
                });

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }
}
