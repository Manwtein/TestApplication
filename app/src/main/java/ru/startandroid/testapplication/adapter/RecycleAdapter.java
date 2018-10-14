package ru.startandroid.testapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.testapplication.R;
import ru.startandroid.testapplication.model.Project;

public class RecycleAdapter
        extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>{
    private final String BASE_URL = "http://gallery.dev.webant.ru/media/";

    private List<Project> projects = new ArrayList<>();
    private Context context;

    public interface OnPopularClickListener {
        void onPopularClick(Bundle bundle);
    }

    private final OnPopularClickListener listener;

    public void setListProjects(List<Project> projects, Context context){
        this.projects = projects;
        this.context = context;
    }

    public RecycleAdapter(OnPopularClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_new, viewGroup, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder viewHolder, int i) {
        Picasso.with(context)
                .load(BASE_URL + projects.get(i).getImage().getContentUrl())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (projects != null)
        return projects.size();
        else return 0;
    }

    public class RecycleViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("name", projects.get(getAdapterPosition()).getName());
            bundle.putString("description", projects.get(getAdapterPosition()).getDescription());
            bundle.putString("contentUrl", BASE_URL + projects.get(getAdapterPosition()).getImage().getContentUrl());
            listener.onPopularClick(bundle);
        }
    }
}
