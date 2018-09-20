package com.jtanveer.fastex.api;

import com.jtanveer.fastex.model.delivery.Delivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FastExWebservice {

    @GET("/deliveries")
    Call<List<Delivery>> getDeliveries(@Query("offset") int offset, @Query("limit") int limit);
}
