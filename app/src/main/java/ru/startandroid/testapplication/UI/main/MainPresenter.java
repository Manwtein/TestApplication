package ru.startandroid.testapplication.UI.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter
        extends MvpPresenter<MainView>{
    private static final int POS_NEW_FRAGMENT = 1;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showFragment(POS_NEW_FRAGMENT);
    }

    public void onTab(int posTab){
        getViewState().showFragment(posTab);
    }

}
