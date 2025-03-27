package com.example.appvideojuegos.vista;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appvideojuegos.R;


public class MainActivity extends AppCompatActivity implements  {
    private SliderView sliderView;
    private RecyclerView recyclerViewGames;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sliderView = findViewById(R.id.sliderView);
        recyclerViewGames = findViewById(R.id.recyclerViewGames);

        // Configurar el RecyclerView con un GridLayout de 2 columnas
        recyclerViewGames.setLayoutManager(new GridLayoutManager(this, 2));

        // Inicializar el presentador
        presenter = new MainPresenter(this);
        presenter.loadGames(); // Cargar datos desde la API
    }

}
