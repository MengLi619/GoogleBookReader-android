package com.example.mengli.myapplication.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.mengli.myapplication.model.Book;
import com.example.mengli.myapplication.service.BookService;

import javax.inject.Inject;

public class MainViewModel extends ViewModelBase {

    private final BookService bookService;
    private final ObservableList<Book> books = new ObservableArrayList<>();

    @Inject
    public MainViewModel(BookService bookService) {
        this.bookService = bookService;
        books.addAll(bookService.getTasks());
    }

    @Bindable
    public ObservableList<Book> getBooks() {
        return books;
    }

    public void refresh() {
        books.clear();
        books.addAll(bookService.getTasks());
    }
}