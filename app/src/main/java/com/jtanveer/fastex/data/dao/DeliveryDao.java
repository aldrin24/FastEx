package com.jtanveer.fastex.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jtanveer.fastex.model.delivery.Delivery;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DeliveryDao {

    @Insert(onConflict = REPLACE)
    void insertDeliveries(List<Delivery> deliveries);

    @Query("SELECT * FROM delivery LIMIT :offset, :count")
    List<Delivery> load(int offset, int count);

    @Query("SELECT * FROM delivery WHERE id = :id LIMIT 1")
    Delivery get(long id);

}
