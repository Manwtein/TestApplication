package ru.startandroid.testapplication.network;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.startandroid.testapplication.model.Response;

public interface ApiService {

    @GET("/api/photos")
    Single<Response> getPhotos(@Query("new") boolean item_new,
                               @Query("popular") boolean item_pop);
}
