package com.xiaoheifamily.bookstore.binding.imageview;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewBindingAdapters {

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
//        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }
}