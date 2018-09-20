package com.jtanveer.fastex.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.jtanveer.fastex.datafactory.DeliveryDataSourceFactory;
import com.jtanveer.fastex.model.NetworkState;
import com.jtanveer.fastex.model.delivery.Delivery;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class DeliveryRepositoryImpl implements DeliveryRepository {

    public static final int PAGE_SIZE = 20;
    public static final int INITIAL_LOAD_SIZE = 40;

    private DeliveryDataSourceFactory deliveryDataSourceFactory;
    private Executor executor;

    @Inject
    public DeliveryRepositoryImpl(DeliveryDataSourceFactory deliveryDataSourceFactory, Executor executor) {
        this.deliveryDataSourceFactory = deliveryDataSourceFactory;
        this.executor = executor;
    }

    @Override
    public LiveData<PagedList<Delivery>> getDeliveries() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build();

        return new LivePagedListBuilder<>(deliveryDataSourceFactory, config)
                .setFetchExecutor(executor)
                .setInitialLoadKey(0)
                .build();
    }

    @Override
    public LiveData<NetworkState> getNetworkState() {
        return Transformations.switchMap(deliveryDataSourceFactory.getDeliveryDataSourceLiveData(), dataSource -> dataSource.networkState);
    }
}
