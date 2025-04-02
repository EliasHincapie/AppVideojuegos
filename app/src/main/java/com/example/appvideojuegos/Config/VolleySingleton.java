package com.example.appvideojuegos.Config;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Clase Singleton para manejar una única instancia de la cola de solicitudes de Volley.
 * Esto evita la creación innecesaria de múltiples colas y mejora la eficiencia de la red.
 */
public class VolleySingleton {
    private static VolleySingleton instance; // Instancia única de la clase
    private RequestQueue requestQueue; // Cola de solicitudes de Volley
    private static Context ctx; // Contexto de la aplicación

    /**
     * Constructor privado para evitar la creación de múltiples instancias.
     * @param context Contexto de la aplicación
     */
    private VolleySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue(); // Inicializa la cola de solicitudes
    }

    /**
     * Metodo estático que devuelve la instancia única de la clase.
     * @param context Contexto de la aplicación
     * @return Instancia única de VolleySingleton
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    /**
     * Metodo que obtiene la cola de solicitudes de Volley.
     * Si no existe, la inicializa.
     * @return Cola de solicitudes de Volley
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // Usamos getApplicationContext() para evitar fugas de memoria
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }
}
