package com.xiaoheifamily.googlebookreader.viewmodel;

import com.xiaoheifamily.googlebookreader.model.Book;

public class BookDetailViewModel extends ViewModelBase {

    private final Book book;

    public BookDetailViewModel(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}