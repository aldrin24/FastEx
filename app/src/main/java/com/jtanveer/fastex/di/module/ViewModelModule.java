package com.jtanveer.fastex.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jtanveer.fastex.di.key.ViewModelKey;
import com.jtanveer.fastex.viewmodel.DeliveryViewModel;
import com.jtanveer.fastex.viewmodel.FactoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryViewModel.class)
    abstract ViewModel bindDeliveryViewModel(DeliveryViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
