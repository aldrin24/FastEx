package com.jtanveer.fastex.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.jtanveer.fastex.model.NetworkState;
import com.jtanveer.fastex.model.delivery.Delivery;

public interface DeliveryRepository {

    LiveData<PagedList<Delivery>> getDeliveries();
    LiveData<NetworkState> getNetworkState();

}
