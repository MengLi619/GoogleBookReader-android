package com.xiaoheifamily.bookstore.binding;

import android.support.annotation.LayoutRes;

public class ItemBinder {

    @LayoutRes
    private final int layout;
    private final int bindingVariable;

    public ItemBinder(int bindingVariable, @LayoutRes int layout) {
        this.bindingVariable = bindingVariable;
        this.layout = layout;
    }

    public int getLayout() {
        return layout;
    }

    public int getBindingVariable() {
        return bindingVariable;
    }
}