package com.jtanveer.fastex.datafactory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.jtanveer.fastex.datasource.DeliveryDataSource;
import com.jtanveer.fastex.model.delivery.Delivery;

import javax.inject.Inject;

public class DeliveryDataSourceFactory extends DataSource.Factory<Integer, Delivery> {

    private DeliveryDataSource deliveryDataSource;
    private MutableLiveData<DeliveryDataSource> deliveryDataSourceLiveData;

    @Inject
    public DeliveryDataSourceFactory(DeliveryDataSource deliveryDataSource) {
        this.deliveryDataSource = deliveryDataSource;
        deliveryDataSourceLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, Delivery> create() {
        deliveryDataSourceLiveData.postValue(deliveryDataSource);
        return deliveryDataSource;
    }

    public LiveData<DeliveryDataSource> getDeliveryDataSourceLiveData() {
        return deliveryDataSourceLiveData;
    }
}
