package ru.startandroid.testapplication.UI.detail;


import android.os.Bundle;
import android.app.Fragment;
import android.app.ActionBar;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import ru.startandroid.testapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment
        extends MvpAppCompatFragment
        implements DetailView{
    @InjectPresenter
            DetailPresenter detailPresenter;

    String name;
    String description;
    String contentUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("myLogs", "onCreate: DetailFragment");
        name = getArguments().getString("name");
        description = getArguments().getString("description");
        contentUrl = getArguments().getString("contentUrl");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initToolbar();
//        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().popBackStack();
//            }
//        });
        ((TextView)view.findViewById(R.id.name)).setText(name);
        ((TextView)view.findViewById(R.id.description)).setText(description);
        Picasso.with(getContext()).load(contentUrl).into((ImageView) view.findViewById(R.id.imageUrl));
        return view;
    }

    private void initToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((MvpAppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((MvpAppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MvpAppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
