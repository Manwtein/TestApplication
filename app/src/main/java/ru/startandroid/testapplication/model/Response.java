package ru.startandroid.testapplication.model;

import java.util.List;


public class Response {

    private List<Project> data = null;

    public List<Project> getProjects(){
        return data;
    }

    public void setProjects(List<Project> data){
        this.data = data;
    }
}
