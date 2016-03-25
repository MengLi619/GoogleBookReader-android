package com.xiaoheifamily.bookstore.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xiaoheifamily.bookstore.BR;
import com.xiaoheifamily.bookstore.R;
import com.xiaoheifamily.bookstore.binding.recyclerview.ItemBinder;
import com.xiaoheifamily.bookstore.functional.Action;
import com.xiaoheifamily.bookstore.utils.Mapper;
import com.xiaoheifamily.bookstore.webapi.BookWebApi;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookListViewModel extends ViewModelBase {

    private static final int PageSize = 10;

    private final BookWebApi bookWebApi;
    private final ObservableList<BookItemViewModel> books = new ObservableArrayList<>();
    private final ItemBinder itemBinder = new ItemBinder(R.layout.book_item, BR.book);

    private int currentIndex;

    @Inject
    public BookListViewModel(BookWebApi bookWebApi) {
        this.bookWebApi = bookWebApi;
    }

    @Bindable
    public ObservableList<BookItemViewModel> getBooks() {
        return books;
    }

    @Bindable
    public ItemBinder getItemBinder() {
        return itemBinder;
    }

    public void refresh(SwipeRefreshLayout layout) {

        bookWebApi.getBooks(0, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> layout.setRefreshing(false))
                .subscribe(newBooks -> {
                    books.clear();
                    books.addAll(Mapper.mapList(newBooks, BookItemViewModel::new));
                    currentIndex = 0;
                });
    }

    @SuppressWarnings("UnusedParameters")
    public void loadMore(RecyclerView view, Action loadFinished) {

        bookWebApi.getBooks(currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(loadFinished::call)
                .subscribe(newBooks -> {
                    if (newBooks.size() > 0) {
                        books.addAll(Mapper.mapList(newBooks, BookItemViewModel::new));
                        currentIndex++;
                    }
                });
    }
}