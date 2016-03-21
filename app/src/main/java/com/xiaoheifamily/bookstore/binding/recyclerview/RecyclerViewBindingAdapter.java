package com.example.mengli.myapplication.binding.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.example.mengli.myapplication.adapter.ObservableRecyclerViewAdapter;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapter {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "itemLayout", "itemBindVariable", "layoutManager"}, requireAll = false)
    public static <T> void setAdapter(RecyclerView recyclerView,
                                      ObservableList<T> items,
                                      @LayoutRes int itemLayout,
                                      int itemBindVariable,
                                      LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setAdapter(new ObservableRecyclerViewAdapter<>(items, itemLayout, itemBindVariable));
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}