package com.xiaoheifamily.googlebookreader.viewmodel;

import android.content.Intent;
import android.view.View;

import com.xiaoheifamily.googlebookreader.model.Book;
import com.xiaoheifamily.googlebookreader.view.BookDetailActivity;
import com.xiaoheifamily.googlebookreader.view.BookDetailFragment;

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