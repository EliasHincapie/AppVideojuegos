package com.example.appvideojuegos;


import android.app.Application;
import com.example.appvideojuegos.Config.VolleySingleton;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Volley singleton
        VolleySingleton.getInstance(getApplicationContext());
    }
}