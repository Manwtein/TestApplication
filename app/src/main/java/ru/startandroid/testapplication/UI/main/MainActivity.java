package ru.startandroid.testapplication.UI.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.startandroid.testapplication.UI.news.NewFragment;
import ru.startandroid.testapplication.UI.popular.PopularFragment;
import ru.startandroid.testapplication.R;

public class MainActivity
        extends MvpAppCompatActivity
        implements MainView {

    private static final int POS_NEW_FRAGMENT = 1;
    private static final int POS_POP_FRAGMENT = 2;
    private static String TAG_NEW = "New";
    private static String TAG_POP = "Popular";

    @InjectPresenter
            MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemReselectedListener(
                new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                mainPresenter.onReselected();
            }
            });
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    int posTab = 0;
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.item_popular:
                                posTab = POS_POP_FRAGMENT;
                                break;
                            case R.id.item_new:
                                posTab = POS_NEW_FRAGMENT;
                                break;
                        }
                        mainPresenter.onTab(posTab);
                        return true;
                    }
                });
    }

    @Override
    public void showFragment(int pos) {
        Fragment fragment = null;
        switch (pos){
            case POS_NEW_FRAGMENT:
                fragment = new NewFragment();
                break;
            case POS_POP_FRAGMENT:
                fragment = new PopularFragment();
                break;
        }
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }

    @Override
    public void backFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_NEW);
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag(TAG_POP);
        if ((fragment != null
                && fragment.isVisible())
                || (fragment2 != null
                && fragment2.isVisible())) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_NEW);
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag(TAG_POP);
        if ((fragment != null
                && fragment.isVisible())
                || (fragment2 != null
                && fragment2.isVisible())) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
