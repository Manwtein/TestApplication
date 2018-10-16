package ru.startandroid.testapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.testapplication.R;
import ru.startandroid.testapplication.model.Photo;

public class RecyclerAdapter
        extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private final String BASE_URL = "http://gallery.dev.webant.ru/media/";

    private List<Photo> photos = new ArrayList<>();
    private Context context;

    public interface OnPopularClickListener {
        void onPopularClick(Bundle bundle);
    }

    private final OnPopularClickListener listener;

    public void setListProjects(List<Photo> photos,
                                Context context){
        this.photos = photos;
        this.context = context;
    }

    public RecyclerAdapter(OnPopularClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                 int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_new, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder,
                                 int i) {
        Glide.with(context)
                .load(BASE_URL + photos.get(i).getImage().getContentUrl())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (photos != null)
        return photos.size();
        else return 0;
    }

    public class RecyclerViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final String NAME = "name";
            final String DESCRIPTION = "description";
            final String URL = "contentUrl";
            Bundle bundle = new Bundle();
            bundle.putString(NAME,
                    photos.get(getAdapterPosition()).getName());
            bundle.putString(DESCRIPTION,
                    photos.get(getAdapterPosition()).getDescription());
            bundle.putString(URL,
                    BASE_URL + photos.get(getAdapterPosition()).getImage().getContentUrl());
            listener.onPopularClick(bundle);
        }
    }
}
