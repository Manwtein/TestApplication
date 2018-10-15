package ru.startandroid.testapplication.UI.popular;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import ru.startandroid.testapplication.model.Photo;
import ru.startandroid.testapplication.model.Response;
import ru.startandroid.testapplication.network.ServiceGenerator;

@InjectViewState
public class PopularPresenter
        extends MvpPresenter<PopularView> {
    private List<Photo> photos;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        request();
    }

    public void request() {
        ServiceGenerator
                .getInstance()
                .getApiService()
                .getPhotos(false, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response>() {
                    @Override
                    public void onSuccess(Response response) {
                        Log.i("myLogs", "response");
                        photos = response.getPhotos();
                        getViewState().setListProjects(photos);
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
