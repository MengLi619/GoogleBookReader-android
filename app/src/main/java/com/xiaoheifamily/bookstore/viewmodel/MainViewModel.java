package com.xiaoheifamily.bookstore.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xiaoheifamily.bookstore.BR;
import com.xiaoheifamily.bookstore.R;
import com.xiaoheifamily.bookstore.binding.recyclerview.ItemBinder;
import com.xiaoheifamily.bookstore.model.Book;
import com.xiaoheifamily.bookstore.service.BookService;

import javax.inject.Inject;

public class MainViewModel extends ViewModelBase {

    private final BookService bookService;
    private final ObservableList<Book> books = new ObservableArrayList<>();
    public final ItemBinder itemBinder = new ItemBinder(R.layout.book_item, BR.book);

    @Inject
    public MainViewModel(BookService bookService) {
        this.bookService = bookService;
        books.addAll(bookService.getBooks());
    }

    @Bindable
    public ObservableList<Book> getBooks() {
        return books;
    }

    public void onRefresh(XRecyclerView recyclerView) {
        books.clear();
        books.addAll(bookService.getBooks());
        recyclerView.refreshComplete();
    }

    public void onLoadMore(XRecyclerView recyclerView) {
        books.addAll(bookService.getBooks());
        recyclerView.loadMoreComplete();
    }
}