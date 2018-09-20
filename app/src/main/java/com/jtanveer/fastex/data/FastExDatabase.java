package com.jtanveer.fastex.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jtanveer.fastex.data.dao.DeliveryDao;
import com.jtanveer.fastex.model.delivery.Delivery;

@Database(entities = {Delivery.class}, version = 1, exportSchema = false)
public abstract class FastExDatabase extends RoomDatabase {
    public static volatile FastExDatabase INSTANCE;
    public abstract DeliveryDao deliveryDao();
}
