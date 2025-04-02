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


 // Actividad principal de la aplicación. Muestra una lista de juegos y un carrusel de destacados.
public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {
    private RecyclerView recyclerViewGames, recyclerViewSlider; // RecyclerViews para la lista y el carrusel
    private ProgressBar progressBar; // Barra de carga
    private MainPresenter presenter; // Presentador que maneja la lógica de la pantalla
    private Button btnFavoritos; // Botón para ver la lista de juegos favoritos

    private Handler handler = new Handler(Looper.getMainLooper()); // Manejador para el auto-scroll del carrusel
    private Runnable runnable; // Tarea para el auto-scroll
    private int currentPosition = 0; // Posición actual en el carrusel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        recyclerViewGames = findViewById(R.id.recyclerViewGames);
        recyclerViewSlider = findViewById(R.id.recyclerViewSlider);
        progressBar = findViewById(R.id.progressBar);
        btnFavoritos = findViewById(R.id.btnFavoritos);

        // Configurar botón para abrir la pantalla de favoritos
        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        // Configurar la lista de juegos en formato de cuadrícula con 2 columnas
        recyclerViewGames.setLayoutManager(new GridLayoutManager(this, 2));

        // Configuración del carrusel (RecyclerView horizontal con efecto de desplazamiento por página)
        recyclerViewSlider.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewSlider); // Vincula el SnapHelper al RecyclerView

        // Inicializar el presentador y solicitar los juegos desde la API
        presenter = new MainPresenterImpl(this);
        presenter.obtenerJuegos();

        // Iniciar el auto-scroll del carrusel
        iniciarCarrusel();
    }

    /**
     * Inicia el auto-scroll del carrusel, cambiando la imagen cada 3 segundos.
     */
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
                handler.postDelayed(this, 3000); // Cambio de imagen cada 3 segundos
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Detener el auto-scroll cuando la actividad se destruye
    }

    //Muestra la barra de progreso mientras se cargan los datos.
    @Override
    public void mostrarCargando() {
        progressBar.setVisibility(View.VISIBLE);
    }


    //Oculta la barra de progreso cuando la carga de datos finaliza.
    @Override
    public void ocultarCargando() {
        progressBar.setVisibility(View.GONE);
    }




    // Muestra la lista de juegos obtenida de la API.
    //@param juegos Lista de juegos a mostrar
    @Override
    public void mostrarJuegos(List<Game> juegos) {
        // Configurar el adaptador para la lista de juegos
        GameAdapter gameAdapter = new GameAdapter(this, juegos, this);
        recyclerViewGames.setAdapter(gameAdapter);

        // Configurar el adaptador para el carrusel con los primeros 5 juegos
        if (juegos != null && !juegos.isEmpty()) {
            int size = Math.min(5, juegos.size()); // Limita a 5 elementos
            recyclerViewSlider.setAdapter(new SliderAdapter(this, juegos.subList(0, size)));
        }
    }



    //Muestra un mensaje de error si ocurre un problema al cargar los datos.
    //@param mensaje Mensaje de error

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, "Error: " + mensaje, Toast.LENGTH_SHORT).show();
    }



    //Maneja el evento de clic en un juego de la lista.
     //Abre la pantalla de detalles del juego seleccionado.
     //@param juego Juego seleccionado
    @Override
    public void onItemClick(Game juego) {
        Intent intent = new Intent(this, GameDetailActivity.class);
        intent.putExtra("game_object", juego); // Pasar el objeto Game completo
        startActivity(intent);
    }



    //Actualiza la lista de favoritos cuando se detectan cambios.
    @Override
    public void actualizarListaFavoritos(List<Game> allFavorites) {
        if (allFavorites != null && !allFavorites.isEmpty()) {
            // Crear y asignar un nuevo adaptador con la lista de favoritos
            GameAdapter favoritosAdapter = new GameAdapter(this, allFavorites, this);
            recyclerViewGames.setAdapter(favoritosAdapter);
        } else {
            // Mostrar mensaje si no hay juegos en favoritos
            Toast.makeText(this, "No tienes juegos en favoritos", Toast.LENGTH_SHORT).show();
        }
    }
}
