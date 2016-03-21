package com.example.mengli.myapplication;

import android.app.Application;

import com.example.mengli.myapplication.component.DaggerViewModelComponent;
import com.example.mengli.myapplication.component.ViewModelComponent;
import com.example.mengli.myapplication.module.ApplicationModule;

public class App extends Application {

    private ViewModelComponent viewModelComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponents();
    }

    public ViewModelComponent getViewModelComponent() {
        return viewModelComponent;
    }

    private void createComponents() {

        viewModelComponent = DaggerViewModelComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}