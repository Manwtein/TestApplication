package ru.startandroid.testapplication.UI.news;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.startandroid.testapplication.model.Photo;

public interface NewView
        extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void setListProjects(List<Photo> photos);

    @StateStrategyType(SkipStrategy.class)
    void showFragment(Bundle bundle);

    @StateStrategyType(SingleStateStrategy.class)
    void showError();
}