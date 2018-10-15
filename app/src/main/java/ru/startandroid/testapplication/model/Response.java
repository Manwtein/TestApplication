package ru.startandroid.testapplication.model;

import java.util.List;


public class Response {

    private List<Photo> data = null;

    public List<Photo> getProjects(){
        return data;
    }

    public void setProjects(List<Photo> data){
        this.data = data;
    }
}
