package com.xiaoheifamily.googlebookreader.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.xiaoheifamily.googlebookreader.BR;
import com.xiaoheifamily.googlebookreader.R;
import com.xiaoheifamily.googlebookreader.binding.ItemBinder;
import com.xiaoheifamily.googlebookreader.functional.Action;
import com.xiaoheifamily.googlebookreader.functional.Action1;
import com.xiaoheifamily.googlebookreader.utils.Mapper;
import com.xiaoheifamily.googlebookreader.webapi.BookWebApi;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookListViewModel extends ViewModelBase {

    private static final int StartPageIndex = 0;
    private static final int PageSize = 10;
    private static final String DefaultQuery = "android";

    private final BookWebApi bookWebApi;
    private final ObservableList<BookItemViewModel> books = new ObservableArrayList<>();
    private final ItemBinder itemBinder = new ItemBinder(R.layout.book_item, BR.model);

    private String currentQuery = DefaultQuery;
    private int currentIndex = StartPageIndex;
    private Action onRefreshing;
    private Action onRefreshFinished;
    private Action onLoadMoreFinished;
    private Action1<Throwable> onError;

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

    public void setOnRefreshing(Action onRefreshing) {
        this.onRefreshing = onRefreshing;
    }

    public void setOnRefreshFinished(Action onRefreshFinished) {
        this.onRefreshFinished = onRefreshFinished;
    }

    public void setOnLoadMoreFinished(Action onLoadMoreFinished) {
        this.onLoadMoreFinished = onLoadMoreFinished;
    }

    public void setOnError(Action1<Throwable> onError) {
        this.onError = onError;
    }

    public void search(String query) {

        if (TextUtils.isEmpty(query)) {
            query = DefaultQuery;
        }

        currentQuery = query;
        refresh();
    }

    public void refresh() {

        onRefreshing.call();

        bookWebApi.getBooks(currentQuery, StartPageIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(onRefreshFinished::call)
                .subscribe(newBooks -> {
                    books.clear();
                    books.addAll(Mapper.mapList(newBooks, BookItemViewModel::new));
                    currentIndex = StartPageIndex;
                }, this::handleError);
    }

    public void loadMore() {

        bookWebApi.getBooks(currentQuery, currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(onLoadMoreFinished::call)
                .subscribe(newBooks -> {
                    if (newBooks.size() > 0) {
                        books.addAll(Mapper.mapList(newBooks, BookItemViewModel::new));
                        currentIndex++;
                    }
                }, this::handleError);
    }

    private void handleError(Throwable throwable) {
        if (onError != null) {
            onError.call(throwable);
        }
    }
}