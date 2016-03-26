package com.xiaoheifamily.bookstore.view;

import android.support.v7.app.AppCompatActivity;

import com.xiaoheifamily.bookstore.App;
import com.xiaoheifamily.bookstore.component.ViewModelComponent;
import com.xiaoheifamily.bookstore.viewmodel.ViewModelBase;

public class ActivityBase<T extends ViewModelBase> extends AppCompatActivity {

    private T model;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    protected ViewModelComponent getViewModelComponent() {
        return ((App) getApplication()).getViewModelComponent();
    }
}