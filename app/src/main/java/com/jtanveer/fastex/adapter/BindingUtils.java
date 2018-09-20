package com.jtanveer.fastex.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jtanveer.fastex.R;

public class BindingUtils {

    @BindingAdapter({"productImageUrl"})
    public static void loadProductImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.box))
                .into(imageView);
    }

}
