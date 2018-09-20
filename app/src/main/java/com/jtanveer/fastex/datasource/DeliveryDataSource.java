package com.jtanveer.fastex.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.jtanveer.fastex.api.FastExWebservice;
import com.jtanveer.fastex.data.dao.DeliveryDao;
import com.jtanveer.fastex.model.NetworkState;
import com.jtanveer.fastex.model.delivery.Delivery;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class DeliveryDataSource extends PageKeyedDataSource<Integer, Delivery> {

    private FastExWebservice webservice;
    private DeliveryDao deliveryDao;
    public final MutableLiveData<NetworkState> networkState;

    @Inject
    public DeliveryDataSource(FastExWebservice webservice, DeliveryDao deliveryDao) {
        this.webservice = webservice;
        this.deliveryDao = deliveryDao;
        networkState = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Delivery> callback) {
        networkState.postValue(NetworkState.LOADING);
        try {
            List<Delivery> deliveries = fetchData(0, params.requestedLoadSize);
            if (deliveries != null) {
                callback.onResult(deliveries, null, params.requestedLoadSize);
            }
            networkState.postValue(NetworkState.LOADED);
        } catch (IOException e) {
            e.printStackTrace();
            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, "Network error occurred!"));
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Delivery> callback) {
        networkState.postValue(NetworkState.LOADING);
        try {
            List<Delivery> deliveries = fetchData(params.key, params.requestedLoadSize);
            int adjacentKey = (params.key >= params.requestedLoadSize) ? params.key - params.requestedLoadSize : null;
            if (deliveries != null) {
                callback.onResult(deliveries, adjacentKey);
            }
            networkState.postValue(NetworkState.LOADED);
        } catch (IOException e) {
            e.printStackTrace();
            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, "Network error occurred!"));
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Delivery> callback) {
        networkState.postValue(NetworkState.LOADING);
        try {
            List<Delivery> deliveries = fetchData(params.key, params.requestedLoadSize);
            int adjacentKey = params.key + params.requestedLoadSize;
            if (deliveries != null) {
                callback.onResult(deliveries, adjacentKey);
            }
            networkState.postValue(NetworkState.LOADED);
        } catch (IOException e) {
            e.printStackTrace();
            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, "Network error occurred!"));
        }
    }

    private List<Delivery> fetchData(int offset, int pageSize) throws IOException {
        List<Delivery> deliveries = deliveryDao.load(offset, pageSize);
        if (deliveries.size() > 0) {
            return deliveries;
        } else {
            Call<List<Delivery>> request = webservice.getDeliveries(offset, pageSize);
            Response<List<Delivery>> response = request.execute();
            if (response != null) {
                deliveries = response.body();
                try {
                    deliveryDao.insertDeliveries(deliveries);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return deliveries;
            } else {
                return null;
            }
        }
    }
}
