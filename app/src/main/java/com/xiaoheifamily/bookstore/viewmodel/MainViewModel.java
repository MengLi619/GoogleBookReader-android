package com.xiaoheifamily.bookstore.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.xiaoheifamily.bookstore.model.Book;
import com.xiaoheifamily.bookstore.service.BookService;

import javax.inject.Inject;

public class MainViewModel extends ViewModelBase {

    private final BookService bookService;
    private final ObservableList<Book> books = new ObservableArrayList<>();

    @Inject
    public MainViewModel(BookService bookService) {
        this.bookService = bookService;
        books.addAll(bookService.getBooks());
    }

    @Bindable
    public ObservableList<Book> getBooks() {
        return books;
    }

    public void refresh() {
        books.clear();
        books.addAll(bookService.getBooks());
    }
}