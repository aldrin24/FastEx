package com.jtanveer.fastex.di.module;

import com.jtanveer.fastex.ui.DeliveriesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract DeliveriesFragment contributeDeliveriesFragment();
}
