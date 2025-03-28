package com.example.appvideojuegos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.appvideojuegos.Modelo.Game;
import com.example.appvideojuegos.R;
import com.smarteist.autoimageslider.SliderViewAdapter;



import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
    private List<Game> gameList;
    private Context context;

    public SliderAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder holder, int position) {
        Game game = gameList.get(position);
        Glide.with(context).load(game.getThumbnail()).into(holder.imageView);
    }

    @Override
    public int getCount() {
        return gameList.size();
    }

    public static class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlider);
        }
    }
}
