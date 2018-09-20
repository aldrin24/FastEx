package com.jtanveer.fastex.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtanveer.fastex.BR;
import com.jtanveer.fastex.R;
import com.jtanveer.fastex.model.NetworkState;
import com.jtanveer.fastex.model.delivery.Delivery;

public class DeliveryAdapter extends PagedListAdapter<Delivery, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private NetworkState networkState;
    private OnItemClickListener listener;

    public DeliveryAdapter(@NonNull DiffUtil.ItemCallback<Delivery> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_delivery, viewGroup, false);
            DeliveryViewHolder viewHolder = new DeliveryViewHolder(itemView);
            itemView.setOnClickListener(view -> listener.onItemClick(getItem(viewHolder.getAdapterPosition())));
            return viewHolder;
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_network_state, viewGroup, false);
            return new NetworkStateViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == TYPE_ITEM) {
            Delivery delivery = getItem(position);
            DeliveryViewHolder deliveryViewHolder = (DeliveryViewHolder) viewHolder;
            deliveryViewHolder.getBinding().setVariable(BR.delivery, delivery);
            deliveryViewHolder.getBinding().executePendingBindings();
        } else {
            NetworkStateViewHolder networkStateViewHolder = (NetworkStateViewHolder) viewHolder;
            networkStateViewHolder.bindView(networkState);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Delivery item);
    }
}
