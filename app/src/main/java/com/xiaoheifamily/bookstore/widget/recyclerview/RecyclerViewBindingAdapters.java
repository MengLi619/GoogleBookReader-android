package com.xiaoheifamily.bookstore.widget.recyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaoheifamily.bookstore.binding.ItemBinder;
import com.xiaoheifamily.bookstore.functional.Action;

@SuppressWarnings("unused")
public class RecyclerViewBindingAdapters {

    public interface LoadMoreListener {
        void onLoadMore(RecyclerView recyclerView, Action loadFinished);
    }

    public static abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

        private final int visibleThreshold;
        private boolean loading;
        private LinearLayoutManager layoutManager;

        public EndlessRecyclerViewScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }

        public void setLoadFinished() {
            loading = false;
        }

        @Override
        public void onScrolled(RecyclerView view, int dx, int dy) {

            if (layoutManager == null) {
                layoutManager = (LinearLayoutManager) view.getLayoutManager();
            }

            int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            int visibleItemCount = view.getChildCount();
            int totalItemCount = layoutManager.getItemCount();

            if (!loading && (firstVisibleItem + visibleThreshold + visibleItemCount) >= totalItemCount) {
                loading = true;
                onLoadMore();
            }
        }

        protected abstract void onLoadMore();
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {

        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter(value = {"loadMore", "visibleThreshold"}, requireAll = false)
    public static void setLoadMore(RecyclerView recyclerView, LoadMoreListener listener, Integer visibleThreshold) {

        if (visibleThreshold == null) {
            visibleThreshold = 0;
        }

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(visibleThreshold) {
            @Override
            public void onLoadMore() {
                listener.onLoadMore(recyclerView, this::setLoadFinished);
            }
        });
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