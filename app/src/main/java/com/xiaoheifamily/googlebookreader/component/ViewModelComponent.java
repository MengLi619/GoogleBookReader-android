package com.xiaoheifamily.googlebookreader.component;

import com.xiaoheifamily.googlebookreader.module.ApplicationModule;
import com.xiaoheifamily.googlebookreader.module.WebApiModule;
import com.xiaoheifamily.googlebookreader.viewmodel.BookListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, WebApiModule.class})
@Singleton
public interface ViewModelComponent {

    BookListViewModel getBookListViewModel();
}