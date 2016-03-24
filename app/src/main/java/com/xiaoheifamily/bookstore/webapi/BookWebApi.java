package com.xiaoheifamily.bookstore.webapi;

import com.xiaoheifamily.bookstore.model.Book;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BookWebApi {

    @GET("books")
    Observable<List<Book>> getBooks(@Query("page") int page, @Query("size") int size);
}