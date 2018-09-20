package com.jtanveer.fastex.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jtanveer.fastex.databinding.ItemNetworkStateBinding;
import com.jtanveer.fastex.model.NetworkState;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {

    private ItemNetworkStateBinding binding;

    public NetworkStateViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bindView(NetworkState networkState) {
        if (networkState != null) {
            if (networkState.getStatus() == NetworkState.Status.RUNNING) {
                binding.progLoading.setVisibility(View.VISIBLE);
            } else {
                binding.progLoading.setVisibility(View.GONE);
            }
            binding.lblMsg.setText(networkState.getMsg());
        }
    }
}
