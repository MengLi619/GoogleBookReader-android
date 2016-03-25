package com.xiaoheifamily.bookstore.viewmodel;

import com.xiaoheifamily.bookstore.model.Book;

public class BookDetailViewModel extends ViewModelBase {

    private final Book book;

    public BookDetailViewModel(Book book) {
        this.book = book;
    }
}