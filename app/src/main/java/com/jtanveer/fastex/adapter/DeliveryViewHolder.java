package com.jtanveer.fastex.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DeliveryViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public DeliveryViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    ViewDataBinding getBinding() {
        return binding;
    }
}
