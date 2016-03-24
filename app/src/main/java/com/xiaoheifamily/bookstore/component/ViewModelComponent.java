package com.xiaoheifamily.bookstore.component;

import com.xiaoheifamily.bookstore.module.ApplicationModule;
import com.xiaoheifamily.bookstore.module.ServiceModule;
import com.xiaoheifamily.bookstore.module.WebApiModule;
import com.xiaoheifamily.bookstore.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ServiceModule.class, WebApiModule.class})
@Singleton
public interface ViewModelComponent {

    MainViewModel getMainViewModel();
}