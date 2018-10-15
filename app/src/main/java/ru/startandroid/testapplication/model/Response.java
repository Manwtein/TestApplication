package ru.startandroid.testapplication.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {

    @SerializedName("data")
    private List<Photo> photos = null;

    public List<Photo> getPhotos(){
        return photos;
    }

    public void setPhotos(List<Photo> photos){
        this.photos = photos;
    }
}
