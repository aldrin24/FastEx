package com.jtanveer.fastex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.jtanveer.fastex.model.NetworkState;
import com.jtanveer.fastex.model.delivery.Delivery;
import com.jtanveer.fastex.repository.DeliveryRepository;

import javax.inject.Inject;

public class DeliveryViewModel extends AndroidViewModel {

    private DeliveryRepository deliveryRepository;

    @Inject
    public DeliveryViewModel(@NonNull Application application, DeliveryRepository deliveryRepository) {
        super(application);
        this.deliveryRepository = deliveryRepository;
    }

    public LiveData<PagedList<Delivery>> getDeliveries() {
        return deliveryRepository.getDeliveries();
    }

    public LiveData<NetworkState> getNetworkState() {
        return deliveryRepository.getNetworkState();
    }
}
