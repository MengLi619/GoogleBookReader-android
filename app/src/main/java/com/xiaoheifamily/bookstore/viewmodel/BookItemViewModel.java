package com.xiaoheifamily.bookstore.viewmodel;

import android.content.Intent;
import android.view.View;

import com.xiaoheifamily.bookstore.model.Book;
import com.xiaoheifamily.bookstore.view.activity.BookDetailActivity;

public class BookItemViewModel extends ViewModelBase {

    private final Book book;

    public BookItemViewModel(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void click(View view) {

        Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
        view.getContext().startActivity(intent);
    }
}