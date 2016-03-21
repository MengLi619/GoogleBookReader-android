package com.example.mengli.myapplication.binding.recyclerview;

import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
public class LayoutManagers {

    @IntDef({LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagerFactory linear() {
        return recyclerView -> new LinearLayoutManager(recyclerView.getContext());
    }

    public static LayoutManagerFactory linear(@Orientation int orientation, boolean reverseLayout) {
        return recyclerView -> new LinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout);
    }

    public static LayoutManagerFactory grid(int spanCount) {
        return recyclerView -> new GridLayoutManager(recyclerView.getContext(), spanCount);
    }

    public static LayoutManagerFactory grid(int spanCount, @Orientation int orientation, boolean reverseLayout) {
        return recyclerView -> new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, reverseLayout);
    }

    public static LayoutManagerFactory staggeredGrid(int spanCount, @Orientation int orientation) {
        return recyclerView -> new StaggeredGridLayoutManager(spanCount, orientation);
    }
}