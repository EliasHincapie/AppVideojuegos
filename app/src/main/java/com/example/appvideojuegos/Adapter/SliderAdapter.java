package com.example.appvideojuegos.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<Game> gameList;
    private Context context;

    public SliderAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Game game = gameList.get(position);
        Log.d("SliderAdapter", "Game: " + game.getTitle() + ", Description: " + game.getShortDescription());

        Glide.with(context).load(game.getThumbnail()).into(holder.imageView);

        if (game.getShortDescription()!= null) {
            holder.Description.setText(game.getShortDescription());
            holder.Description.setVisibility(View.VISIBLE);
        }else {
            holder.Description.setVisibility(View.GONE);
        }

    }



    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Description;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imageView);
            Description = itemView.findViewById(R.id.gameDescription);
        }
    }
}

