package ru.startandroid.testapplication.model;

import java.util.List;


public class Response {

    private List<Photo> data = null;

    public List<Photo> getPhotos(){
        return data;
    }

    public void setPhotos(List<Photo> data){
        this.data = data;
    }
}
