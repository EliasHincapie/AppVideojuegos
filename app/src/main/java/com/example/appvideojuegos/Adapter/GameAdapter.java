package com.example.appvideojuegos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.R;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private List<Game> gameList;
    private Context context;

    private OnItemClickListener listener;


    public GameAdapter(Context context, List<Game> gameList, OnItemClickListener listener) {
        this.context = context;
        this.gameList = gameList;
        this.listener = listener;

    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.bind(game, listener);

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView title,shortDescription;
        ImageView image;

        public GameViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.gameTitle);
            shortDescription = itemView.findViewById(R.id.gameShortDescription);
            image = itemView.findViewById(R.id.gameImage);
        }

        public void bind(final Game game, final OnItemClickListener listener) {
            title.setText(game.getTitle());
            shortDescription.setText(game.getShortDescription());
            Glide.with(itemView.getContext()).load(game.getThumbnail()).into(image);
            itemView.setOnClickListener(v -> listener.onItemClick(game));

        }
    }
}

