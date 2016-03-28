package com.xiaoheifamily.googlebookreader;

import android.app.Application;

import com.xiaoheifamily.googlebookreader.component.DaggerViewModelComponent;
import com.xiaoheifamily.googlebookreader.component.ViewModelComponent;
import com.xiaoheifamily.googlebookreader.module.ApplicationModule;
import com.xiaoheifamily.googlebookreader.module.WebApiModule;

public class App extends Application {

    private static final String BaseUrl = "http://xiaoheifamily.com/google-book-reader/";
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
                .webApiModule(new WebApiModule(BaseUrl))
                .build();
    }
}