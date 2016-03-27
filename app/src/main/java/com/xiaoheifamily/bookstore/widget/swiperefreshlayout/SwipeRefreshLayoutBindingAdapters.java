package com.xiaoheifamily.bookstore.widget.swiperefreshlayout;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutBindingAdapters {

    @BindingAdapter("onRefresh")
    public static <T> void setOnRefreshListener(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener) {

        layout.setOnRefreshListener(listener::onRefresh);
    }
}