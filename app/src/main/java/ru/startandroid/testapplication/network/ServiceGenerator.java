package ru.startandroid.testapplication.network;

import java.io.IOException;

import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private final String BASE_URL = "http://gallery.dev.webant.ru";
    private final String KEY_TAG = "authorization" ;
    private final String KEY_VALUE = "manwtein" ;

    private ApiService apiService;
    private static ServiceGenerator instance = null;

    public ServiceGenerator() {
        buildRetrofit(BASE_URL);
    }

    public static ServiceGenerator getInstance(){
        if (instance == null) instance = new ServiceGenerator();
        return instance;
    }

    private void buildRetrofit(String base_url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain)
                            throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .header(KEY_TAG, KEY_VALUE)
                                .build();

                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.
                        createWithScheduler(Schedulers.io()))
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService(){
        return apiService;
    }
}
