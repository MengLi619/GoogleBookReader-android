package com.xiaoheifamily.bookstore.binding.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xiaoheifamily.bookstore.adapter.ObservableRecyclerViewAdapter;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapters {

    public interface OnRecyclerViewRefresh {
        void onRefresh(XRecyclerView recyclerView);
    }

    public interface OnRecyclerViewLoadMore {
        void onLoadMore(XRecyclerView recyclerView);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"items", "itemBinder"})
    public static <T> void setAdapter(RecyclerView recyclerView, ObservableList<T> items, ItemBinder itemBinder) {

        // Only set adapter at first time.
        if (recyclerView.getAdapter() == null) {

            recyclerView.setAdapter(new ObservableRecyclerViewAdapter<>(items, itemBinder.getLayout(),
                    itemBinder.getBindingVariable()));
        }
    }

    @BindingAdapter({"onRefresh", "onLoadMore"})
    public static void setEvents(XRecyclerView recyclerView, OnRecyclerViewRefresh onRefresh, OnRecyclerViewLoadMore onLoadMore) {

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                onRefresh.onRefresh(recyclerView);
            }

            @Override
            public void onLoadMore() {
                onLoadMore.onLoadMore(recyclerView);
            }
        });
    }
}