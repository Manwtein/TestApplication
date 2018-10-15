package ru.startandroid.testapplication.UI.news;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.startandroid.testapplication.R;
import ru.startandroid.testapplication.UI.detail.DetailFragment;
import ru.startandroid.testapplication.adapter.RecyclerAdapter;
import ru.startandroid.testapplication.model.Project;

public class NewFragment
        extends MvpAppCompatFragment
        implements NewView {

    @InjectPresenter
    NewPresenter newPresenter;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private LinearLayout new_no_connect;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_new, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        initToolbar();
        initSwipe(view);
        progressBar = view.findViewById(R.id.pb_new);
        new_no_connect = view.findViewById(R.id.new_no_connect);
        initRecycler(view);
    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recycleView_new);
        GridLayoutManager gridLayoutManager;
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(getActivity(),3);
        } else {
            gridLayoutManager = new GridLayoutManager(getActivity(),2);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapter = new RecyclerAdapter(new RecyclerAdapter.OnPopularClickListener() {
            @Override
            public void onPopularClick(Bundle bundle) {
                newPresenter.onPopularClick(bundle);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initSwipe(View view) {
        swipeRefreshLayout = view.findViewById(R.id.srl_new);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorTitle);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressBar.setVisibility(View.VISIBLE);
                new_no_connect.setVisibility(View.GONE);
                newPresenter.request();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("New");
        ((MvpAppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((MvpAppCompatActivity)getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(false);
        ((MvpAppCompatActivity)getActivity()).getSupportActionBar()
                .setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("myLogs", "onCreate: new");
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setListProjects(List<Project> projects) {
        hideProgressBar();
        recyclerView.setVisibility(View.VISIBLE);
        recyclerAdapter.setListProjects(projects, getContext());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFragment(Bundle bundle) {
        Fragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.GONE);
        hideProgressBar();
        new_no_connect.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
