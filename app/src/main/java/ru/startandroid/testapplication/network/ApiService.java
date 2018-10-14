package ru.startandroid.testapplication.network;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.startandroid.testapplication.model.Response;

public interface ApiService {

    @GET("/api/photos")
    Single<Response> getNews(@Query("new") boolean new_items, @Query("popular") boolean new_populars);
}
