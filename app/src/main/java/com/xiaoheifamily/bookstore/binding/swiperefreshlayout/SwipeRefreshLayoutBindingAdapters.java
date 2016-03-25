package com.xiaoheifamily.bookstore.binding.swiperefreshlayout;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutBindingAdapters {

    public interface OnRefreshListener {
        void onRefresh(SwipeRefreshLayout layout);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("onRefresh")
    public static <T> void setOnRefreshListener(SwipeRefreshLayout layout, OnRefreshListener listener) {

        layout.setOnRefreshListener(() -> listener.onRefresh(layout));
    }
}