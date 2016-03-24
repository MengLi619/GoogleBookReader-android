package com.xiaoheifamily.bookstore.utils;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

public final class ObservableListUtils {

    public static <T> ObservableList<T> fromList(List<T> list) {

        ObservableList<T> observableList = new ObservableArrayList<>();
        observableList.addAll(list);

        return observableList;
    }
}
