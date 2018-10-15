package ru.startandroid.testapplication.UI.popular;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.startandroid.testapplication.model.Project;

public interface PopularView
        extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void setListProjects(List<Project> projects);

    @StateStrategyType(SkipStrategy.class)
    void showFragment(Bundle bundle);

    @StateStrategyType(SingleStateStrategy.class)
    void showError();
}
