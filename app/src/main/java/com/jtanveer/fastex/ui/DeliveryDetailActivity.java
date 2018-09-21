package com.jtanveer.fastex.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jtanveer.fastex.R;
import com.jtanveer.fastex.databinding.ActivityDeliveryDetailBinding;
import com.jtanveer.fastex.model.delivery.Delivery;

import dagger.android.AndroidInjection;

import static com.jtanveer.fastex.util.Constants.KEY_DELIVERY;

public class DeliveryDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityDeliveryDetailBinding binding;

    private MapView mapViewLocation;
    private Delivery delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_detail);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configureDagger();

        mapViewLocation = binding.content.mapLocation;
        mapViewLocation.onCreate(savedInstanceState);
        mapViewLocation.getMapAsync(this);
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    private void updateUI(GoogleMap googleMap) {
        delivery = getIntent().getParcelableExtra(KEY_DELIVERY);
        if (delivery != null) {
            LatLng latLng = new LatLng(delivery.getLocation().getLat(), delivery.getLocation().getLng());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            googleMap.addMarker(markerOptions);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            binding.content.setDelivery(delivery);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapViewLocation.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapViewLocation.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapViewLocation.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapViewLocation.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapViewLocation.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewLocation.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        updateUI(googleMap);
    }
}
