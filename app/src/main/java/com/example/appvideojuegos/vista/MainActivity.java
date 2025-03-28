package com.example.appvideojuegos.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appvideojuegos.Adapter.GameAdapter;
import com.example.appvideojuegos.Adapter.SliderAdapter;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Presentador.MainPresenter;
import com.example.appvideojuegos.Presentador.MainPresenterImpl;
import com.example.appvideojuegos.Presentador.MainView;
import com.example.appvideojuegos.R;
import com.smarteist.autoimageslider.SliderView; // ✅ Importación añadida

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private RecyclerView recyclerView;
    private SliderView sliderView;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewGames);
        sliderView = findViewById(R.id.sliderView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // ✅ Ahora en formato de cuadros

        presenter = new MainPresenterImpl(this);
        presenter.obtenerJuegos(); // Cargar datos desde la API
    }

    @Override
    public void mostrarCargando() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarCargando() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarJuegos(List<Game> juegos) {
        recyclerView.setAdapter(new GameAdapter(this, juegos));

        // Cargar juegos en el carrusel (solo los primeros 5)
        sliderView.setSliderAdapter(new SliderAdapter(this, juegos.subList(0, Math.min(5, juegos.size()))));
    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
