package com.xiaoheifamily.bookstore.binding.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.xiaoheifamily.bookstore.adapter.ObservableRecyclerViewAdapter;
import com.xiaoheifamily.bookstore.binding.ItemBinder;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapter {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "itemBinder", "layoutManager"})
    public static <T> void setAdapter(RecyclerView recyclerView,
                                      ObservableList<T> items,
                                      ItemBinder itemBinder,
                                      LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
        recyclerView.setAdapter(new ObservableRecyclerViewAdapter<>(items, itemBinder.getLayout(),
                itemBinder.getBindingVariable()));
    }
}