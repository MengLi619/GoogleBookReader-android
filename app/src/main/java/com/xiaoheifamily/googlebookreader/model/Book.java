package com.xiaoheifamily.googlebookreader.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xiaoheifamily.googlebookreader.BR;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Book extends BaseObservable implements Serializable {

    private String id;
    private String title;
    private String description;
    private String image;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }
}