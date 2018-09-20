package com.jtanveer.fastex.di.module;

import com.jtanveer.fastex.ui.DeliveriesActivity;
import com.jtanveer.fastex.ui.DeliveryDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DeliveriesActivity contributeDeliveriesActivity();

    @ContributesAndroidInjector
    abstract DeliveryDetailActivity contributeDeliveryDetailActivity();
}
