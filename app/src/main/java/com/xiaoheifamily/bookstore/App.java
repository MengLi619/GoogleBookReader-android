package com.xiaoheifamily.bookstore;

import android.app.Application;

import com.xiaoheifamily.bookstore.component.DaggerViewModelComponent;
import com.xiaoheifamily.bookstore.component.ViewModelComponent;
import com.xiaoheifamily.bookstore.module.ApplicationModule;

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