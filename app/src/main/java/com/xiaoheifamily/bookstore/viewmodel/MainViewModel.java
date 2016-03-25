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
import com.xiaoheifamily.bookstore.model.Book;
import com.xiaoheifamily.bookstore.utils.ObservableListUtils;
import com.xiaoheifamily.bookstore.webapi.BookWebApi;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainViewModel extends ViewModelBase {

    private static final int PageSize = 10;

    private final BookWebApi bookWebApi;
    private ObservableList<Book> books = new ObservableArrayList<>();
    private final ItemBinder itemBinder = new ItemBinder(R.layout.book_item, BR.book);

    private int currentIndex;

    @Inject
    public MainViewModel(BookWebApi bookWebApi) {

        this.bookWebApi = bookWebApi;

        bookWebApi.getBooks(currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books::addAll);
    }

    @Bindable
    public ObservableList<Book> getBooks() {
        return books;
    }

    @Bindable
    public ItemBinder getItemBinder() {
        return itemBinder;
    }

    public void refresh(SwipeRefreshLayout layout) {

        currentIndex = 0;

        bookWebApi.getBooks(currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> layout.setRefreshing(false))
                .subscribe(x -> {
                    books.clear();
                    books.addAll(x);
                });
    }

    public void loadMore(RecyclerView view, Action loadFinished) {

        bookWebApi.getBooks(currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(loadFinished::call)
                .subscribe(x -> {
                    books.addAll(ObservableListUtils.fromList(x));
                });
    }
}