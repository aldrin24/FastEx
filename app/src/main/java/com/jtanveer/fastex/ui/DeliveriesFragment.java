package com.jtanveer.fastex.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtanveer.fastex.R;
import com.jtanveer.fastex.adapter.DeliveryAdapter;
import com.jtanveer.fastex.databinding.FragmentDeliveriesBinding;
import com.jtanveer.fastex.model.delivery.Delivery;
import com.jtanveer.fastex.viewmodel.DeliveryViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.jtanveer.fastex.util.Constants.KEY_DELIVERY;

/**
 * A placeholder fragment containing a simple view.
 */
public class DeliveriesFragment extends Fragment implements DeliveryAdapter.OnItemClickListener {

    private FragmentDeliveriesBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public DeliveriesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deliveries, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        configureUI();
    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureUI() {
        DeliveryViewModel deliveryViewModel = ViewModelProviders.of(this, viewModelFactory).get(DeliveryViewModel.class);
        DeliveryAdapter adapter = new DeliveryAdapter(Delivery.DIFF_CALLBACK, this);
        deliveryViewModel.getDeliveries().observe(this, pagedList -> {
            adapter.submitList(pagedList);
            binding.progLoading.setVisibility(View.GONE);
        });
        deliveryViewModel.getNetworkState().observe(this, adapter::setNetworkState);
        binding.rvDeliveries.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvDeliveries.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Delivery item) {
        Intent intent = new Intent(getContext(), DeliveryDetailActivity.class);
        intent.putExtra(KEY_DELIVERY, item);
        startActivity(intent);
    }
}
