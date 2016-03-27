package com.xiaoheifamily.bookstore.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class EndlessRecyclerView extends RecyclerView {

    public interface LoadMoreListener {
        void onLoadMore();
    }

    private static final int DefaultVisibleThreshold = 0;

    private LoadMoreListener loadMoreListener;
    private int visibleThreshold = DefaultVisibleThreshold;
    private boolean loading;

    public EndlessRecyclerView(Context context) {
        super(context);
    }

    public EndlessRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EndlessRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (loadMoreListener == null) {
            return;
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();

        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = getChildCount();
        int totalItemCount = layoutManager.getItemCount();

        if (!loading && (firstVisibleItem + visibleThreshold + visibleItemCount) >= totalItemCount) {
            loading = true;
            loadMoreListener.onLoadMore();
        }
    }
}
