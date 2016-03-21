package com.xiaoheifamily.bookstore.module;

import com.xiaoheifamily.bookstore.service.BookService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    public BookService provideTaskService() {
        return new BookService();
    }
}
