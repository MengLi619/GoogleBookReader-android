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
import com.xiaoheifamily.bookstore.utils.Mapper;
import com.xiaoheifamily.bookstore.utils.ObservableListUtils;
import com.xiaoheifamily.bookstore.webapi.BookWebApi;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookListViewModel extends ViewModelBase {

    private static final int PageSize = 10;

    private final BookWebApi bookWebApi;
    private ObservableList<BookViewModel> books = new ObservableArrayList<>();
    private final ItemBinder itemBinder = new ItemBinder(R.layout.book_item, BR.book);

    private int currentIndex;

    @Inject
    public BookListViewModel(BookWebApi bookWebApi) {

        this.bookWebApi = bookWebApi;

        bookWebApi.getBooks(0, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
                    books.addAll(Mapper.mapList(x, BookViewModel::new));
                });
    }

    @Bindable
    public ObservableList<BookViewModel> getBooks() {
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
                .subscribe(x -> {
                    books.clear();
                    books.addAll(Mapper.mapList(x, BookViewModel::new));
                    currentIndex = 0;
                });
    }

    @SuppressWarnings("UnusedParameters")
    public void loadMore(RecyclerView view, Action loadFinished) {

        bookWebApi.getBooks(currentIndex, PageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(loadFinished::call)
                .subscribe(x -> {
                    ObservableList<Book> books = ObservableListUtils.fromList(x);
                    if (books.size() > 0) {
                        this.books.addAll(Mapper.mapList(x, BookViewModel::new));
                        currentIndex++;
                    }
                });
    }
}