package com.jtanveer.fastex.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jtanveer.fastex.api.FastExWebservice;
import com.jtanveer.fastex.data.FastExDatabase;
import com.jtanveer.fastex.data.dao.DeliveryDao;
import com.jtanveer.fastex.datafactory.DeliveryDataSourceFactory;
import com.jtanveer.fastex.datasource.DeliveryDataSource;
import com.jtanveer.fastex.repository.DeliveryRepository;
import com.jtanveer.fastex.repository.DeliveryRepositoryImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // --- DATABASE INJECTION ---

    @Provides
    @Singleton
    FastExDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                FastExDatabase.class, "fastex.db")
                .build();
    }

    @Provides
    @Singleton
    DeliveryDao provideDeliveryDao(FastExDatabase database) {
        return database.deliveryDao();
    }

    // --- NETWORK INJECTION ---

    private static final String BASE_URL = "https://mock-api-mobile.dev.lalamove.com/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    FastExWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(FastExWebservice.class);
    }

    // --- REPOSITORY INJECTION ---

    @Provides
    @Singleton
    DeliveryDataSource provideDeliveryDataSource(FastExWebservice webservice, DeliveryDao deliveryDao) {
        return new DeliveryDataSource(webservice, deliveryDao);
    }

    @Provides
    @Singleton
    DeliveryDataSourceFactory provideDeliveryDataSourceFactory(DeliveryDataSource deliveryDataSource) {
        return new DeliveryDataSourceFactory(deliveryDataSource);
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    DeliveryRepository provideDeliveryRepository(DeliveryDataSourceFactory deliveryDataSourceFactory, Executor executor) {
        return new DeliveryRepositoryImpl(deliveryDataSourceFactory, executor);
    }
}
