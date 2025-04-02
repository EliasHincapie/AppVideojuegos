package com.example.appvideojuegos.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appvideojuegos.Adapter.GameAdapter;
import com.example.appvideojuegos.Adapter.OnItemClickListener;
import com.example.appvideojuegos.Adapter.SliderAdapter;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.Presentador.MainPresenter;
import com.example.appvideojuegos.Modelo.MainPresenterImpl;
import com.example.appvideojuegos.Presentador.MainView;
import com.example.appvideojuegos.R;
import java.util.List;

import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.widget.LinearSmoothScroller;


public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {
    private RecyclerView recyclerViewGames, recyclerViewSlider;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    private Button btnFavoritos;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPosition = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewGames = findViewById(R.id.recyclerViewGames);
        recyclerViewSlider = findViewById(R.id.recyclerViewSlider);
        progressBar = findViewById(R.id.progressBar);
        btnFavoritos = findViewById(R.id.btnFavoritos);

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

            // Lista de juegos en formato de cuadros (2 columnas)
        recyclerViewGames.setLayoutManager(new GridLayoutManager(this, 2));

        // Configuración del carrusel (RecyclerView horizontal con PagerSnapHelper)
        recyclerViewSlider.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewSlider);

        presenter = new MainPresenterImpl(this);
        presenter.obtenerJuegos(); // Cargar datos desde la API
        iniciarCarrusel(); // Llamamos a la función para que inicie el auto-scroll


    }

    private void iniciarCarrusel() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (recyclerViewSlider.getAdapter() != null) {
                    int itemCount = recyclerViewSlider.getAdapter().getItemCount();
                    if (itemCount > 0) {
                        currentPosition = (currentPosition + 1) % itemCount;
                        recyclerViewSlider.smoothScrollToPosition(currentPosition);
                    }
                }
                handler.postDelayed(this, 3000); // Cambia cada 3 segundos
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
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

        // Configurar el adaptador para la lista de juegos con el listener de clics
        GameAdapter gameAdapter = new GameAdapter(this, juegos, (OnItemClickListener) this);
        recyclerViewGames.setAdapter(gameAdapter);

        if (juegos != null && !juegos.isEmpty()) {
            int size = Math.min(5, juegos.size());
            recyclerViewSlider.setAdapter(new SliderAdapter(this, juegos.subList(0, size)));
        }

    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Game juego) {

        // Navegar a la pantalla de detalles del juego seleccionado
        Intent intent = new Intent(this, GameDetailActivity.class);
        intent.putExtra("game_object", juego); // Pasar el objeto Game completo como extra
        startActivity(intent);

    }

    @Override
    public void actualizarListaFavoritos(List<Game> allFavorites) {
        if (allFavorites != null && !allFavorites.isEmpty()) {

            // Crear y asignar un nuevo adaptador con la lista de favoritos
            GameAdapter favoritosAdapter = new GameAdapter(this, allFavorites, this);
            recyclerViewGames.setAdapter(favoritosAdapter);
        } else {

            // Mostrar un mensaje si la lista de favoritos está vacía
            Toast.makeText(this, "No tienes juegos en favoritos", Toast.LENGTH_SHORT).show();
        }

    }

}
