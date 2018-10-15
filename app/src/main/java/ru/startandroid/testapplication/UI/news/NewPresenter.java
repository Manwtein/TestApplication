package ru.startandroid.testapplication.UI.news;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import ru.startandroid.testapplication.model.Project;
import ru.startandroid.testapplication.model.Response;
import ru.startandroid.testapplication.network.ServiceGenerator;

@InjectViewState
public class NewPresenter
        extends MvpPresenter<NewView> {
    private List<Project> projects;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        request();
    }

    public void request() {
        ServiceGenerator
                .getInstance()
                .getApiService()
                .getPhotos(true, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response>() {
                    @Override
                    public void onSuccess(Response response) {
                        Log.i("myLogs", "response");
                        projects = response.getProjects();
                        getViewState().setListProjects(projects);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("myLogs", "onError: " + e.getMessage());
                        getViewState().showError();
                    }
                });
    }

    public void onPopularClick(Bundle bundle){
        getViewState().showFragment(bundle);
    }

}
