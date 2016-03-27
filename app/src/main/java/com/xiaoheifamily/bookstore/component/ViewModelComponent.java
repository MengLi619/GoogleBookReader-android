package com.xiaoheifamily.bookstore.component;

import com.xiaoheifamily.bookstore.module.ApplicationModule;
import com.xiaoheifamily.bookstore.module.WebApiModule;
import com.xiaoheifamily.bookstore.viewmodel.BookListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, WebApiModule.class})
@Singleton
public interface ViewModelComponent {

    BookListViewModel getBookListViewModel();
}