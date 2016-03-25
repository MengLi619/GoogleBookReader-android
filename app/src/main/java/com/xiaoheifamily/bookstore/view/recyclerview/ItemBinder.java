package com.xiaoheifamily.bookstore.binding.recyclerview;

import android.support.annotation.LayoutRes;

public class ItemBinder {

    @LayoutRes
    private final int layout;
    private final int bindingVariable;

    public ItemBinder(@LayoutRes int layout, int bindingVariable) {
        this.layout = layout;
        this.bindingVariable = bindingVariable;
    }

    public int getLayout() {
        return layout;
    }

    public int getBindingVariable() {
        return bindingVariable;
    }
}