package com.xiaoheifamily.bookstore.viewmodel;

import android.content.Intent;
import android.view.View;

import com.xiaoheifamily.bookstore.model.Book;
import com.xiaoheifamily.bookstore.view.BookDetailActivity;
import com.xiaoheifamily.bookstore.view.BookDetailFragment;

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
        intent.putExtra(BookDetailFragment.ARG_BOOK, book);

        view.getContext().startActivity(intent);
    }
}