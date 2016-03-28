package com.xiaoheifamily.googlebookreader.widget.imageview;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class ImageViewBindingAdapters {

    @BindingAdapter(value = {"imageUrl", "circle"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Boolean circle) {

        if (circle == null || !circle) {
            Glide.with(view.getContext()).load(url).into(view);
        } else {
            Glide.with(view.getContext()).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(view.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    view.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }
}