package com.example.mengli.myapplication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Book extends BaseObservable {

    private int id;
    private String title;
    private String description;
    private String image;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}