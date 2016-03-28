package com.xiaoheifamily.googlebookreader.widget.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.xiaoheifamily.googlebookreader.binding.ItemBinder;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapters {

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter(value = {"loadMore", "visibleThreshold"}, requireAll = false)
    public static void setLoadMore(EndlessRecyclerView recyclerView,
                                   EndlessRecyclerView.LoadMoreListener loadMore,
                                   Integer visibleThreshold) {

        recyclerView.setLoadMoreListener(loadMore);

        if (visibleThreshold != null) {
            recyclerView.setVisibleThreshold(visibleThreshold);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"items", "itemBinder"})
    public static <T> void setAdapter(RecyclerView recyclerView, ObservableList<T> items, ItemBinder itemBinder) {

        ObservableRecyclerViewAdapter<T> adapter =
                (ObservableRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter == null) {
            adapter = new ObservableRecyclerViewAdapter<>(items, itemBinder.getLayout(), itemBinder.getBindingVariable());
            recyclerView.setAdapter(adapter);

        } else {
            adapter.setItems(items);
        }
    }
}