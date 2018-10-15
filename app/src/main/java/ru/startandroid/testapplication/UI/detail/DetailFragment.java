package ru.startandroid.testapplication.UI.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.squareup.picasso.Picasso;

import ru.startandroid.testapplication.R;

public class DetailFragment
        extends Fragment {

    String name;
    String description;
    String contentUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final String NAME = "name";
        final String DESCRIPTION = "description";
        final String URL = "contentUrl";
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            description = getArguments().getString(DESCRIPTION);
            contentUrl = getArguments().getString(URL);
        }
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initToolbar();
        ((TextView)view.findViewById(R.id.name)).setText(name);
        ((TextView)view.findViewById(R.id.description)).setText(description);
        Picasso.with(getContext())
                .load(contentUrl)
                .into((ImageView) view.findViewById(R.id.imageUrl));
        return view;
    }

    private void initToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((MvpAppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((MvpAppCompatActivity)getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        ((MvpAppCompatActivity)getActivity())
                .getSupportActionBar()
                .setDisplayShowHomeEnabled(true);
    }
}
