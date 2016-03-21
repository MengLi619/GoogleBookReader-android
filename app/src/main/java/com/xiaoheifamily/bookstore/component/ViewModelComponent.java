package com.xiaoheifamily.bookstore.component;

import com.xiaoheifamily.bookstore.module.ApplicationModule;
import com.xiaoheifamily.bookstore.module.ServiceModule;
import com.xiaoheifamily.bookstore.viewmodel.MainViewModel;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface ViewModelComponent {

    MainViewModel getMainViewModel();
}