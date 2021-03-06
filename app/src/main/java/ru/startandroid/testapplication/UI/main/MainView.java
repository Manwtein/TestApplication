package ru.startandroid.testapplication.UI.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView
        extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showFragment(int pos);

    @StateStrategyType(SkipStrategy.class)
    void backFragment();
}
