package com.xiaoheifamily.bookstore.binding.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xiaoheifamily.bookstore.adapter.ObservableRecyclerViewAdapter;
import com.xiaoheifamily.bookstore.binding.ItemBinder;
import com.xiaoheifamily.bookstore.functional.Action;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = "layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "itemBinder"})
    public static <T> void setAdapter(RecyclerView recyclerView, ObservableList<T> items, ItemBinder itemBinder) {

        recyclerView.setAdapter(new ObservableRecyclerViewAdapter<>(items, itemBinder.getLayout(),
                itemBinder.getBindingVariable()));
    }

    @BindingAdapter(value = {"onRefresh", "onLoadMore"})
    public static void setEvents(XRecyclerView recyclerView, Action onRefresh, Action onLoadMore) {

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                onRefresh.invoke();
            }

            @Override
            public void onLoadMore() {
                onLoadMore.invoke();
            }
        });
    }
}