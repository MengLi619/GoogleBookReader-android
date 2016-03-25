package com.xiaoheifamily.bookstore.viewmodel;

import com.xiaoheifamily.bookstore.model.Book;

public class BookViewModel extends ViewModelBase {

    private final Book book;

    public BookViewModel(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}