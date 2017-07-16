package com.batchmates.android.darkwaters.view.secondactivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.batchmates.android.darkwaters.R;
import com.batchmates.android.darkwaters.injection.secondactivity.DaggerSecondActivityComponent;
import com.batchmates.android.darkwaters.model.ClosePlaces;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import javax.inject.Inject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,android.location.LocationListener,SecondActivityContract.View,GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "Map";
    private GoogleMap mMap;
    private int mMaxEntries=5;
    private FusedLocationProviderClient fusedLocation;
    private SupportMapFragment mapFragment;
    private LatLng sydney;
    private Location currentLocation;
    private GoogleApiClient googleApiClient;
    private BottomNavigationView bottomNavigationView;


    //recycler Setup
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator itemAnim=new DefaultItemAnimator();

    @Inject SecondActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomListener);

        PlaceAutocompleteFragment autoFrag=(PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autoFrag.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d(TAG, "onPlaceSelected: "+place.getName());
                mMap.addMarker(new MarkerOptions().title(place.getName().toString()).snippet(place.getAddress().toString()).position(place.getLatLng()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),150));
                CameraUpdate camera=CameraUpdateFactory.newLatLngZoom(place.getLatLng(),15);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera(camera);

            }

            @Override
            public void onError(Status status) {
                Log.d(TAG, "onError: "+status);

            }
        });

        
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.store:
                        Log.d(TAG, "onNavigationItemSelected: Selected store");
                        recycler.setAdapter(null);
                        PendingResult<PlaceLikelihoodBuffer> result= Places.PlaceDetectionApi.getCurrentPlace(googleApiClient,null);
                        presenter.listOfNeerPlacesStore(result);
                        break;

                    case R.id.any:
                        Log.d(TAG, "onNavigationItemSelected: Selected any");
                        recycler.setAdapter(null);
                        PendingResult<PlaceLikelihoodBuffer> result2= Places.PlaceDetectionApi.getCurrentPlace(googleApiClient,null);
                        presenter.listOfNeerPlaces(result2);
                        break;

                    case R.id.food:
                        Log.d(TAG, "onNavigationItemSelected: Selected food");
                        recycler.setAdapter(null);
                        PendingResult<PlaceLikelihoodBuffer> result3= Places.PlaceDetectionApi.getCurrentPlace(googleApiClient,null);
                        presenter.listOfNeerPlacesFood(result3);
                        break;
                }
                return true;
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        recycler=(RecyclerView)findViewById(R.id.recycleView);
        mapFragment.getMapAsync(this);

        googleApiClient= new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        googleApiClient.connect();
        setUpDagger();
        PendingResult<PlaceLikelihoodBuffer> result= Places.PlaceDetectionApi.getCurrentPlace(googleApiClient,null);

        //get list of places neer me
        presenter.addview(this);
        presenter.listOfNeerPlaces(result);

        locationTracker();
    }

    private void setUpDagger() {
        DaggerSecondActivityComponent.create().inject(this);
    }

    

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json));

        Log.d(TAG, "onMapReady: map is set");
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d(TAG, "onMarkerClick: "+marker.getTitle());
//                marker.setSnippet("Your Mama Was here");
                return false;
            }
        });
//        sydney = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void locationTracker() {

        fusedLocation = LocationServices.getFusedLocationProviderClient(this);

        fusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation=location;
                sydney=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                CameraUpdate cam=CameraUpdateFactory.newLatLngZoom(sydney,15);
                mMap.animateCamera(cam);
                    Log.d(TAG, "onSuccess: " + location.getLongitude() + " Lat: " + location.getLatitude() + " " + location);
            }
        });

        LocationManager locationManger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation=location;
//        sydney=new LatLng(location.getLatitude(),location.getLongitude());
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: Connection Failed");
    }

    @Override
    public void getClosePlaces(List<ClosePlaces> listPlaces) {

        Log.d(TAG, "getClosePlaces: "+listPlaces.size());
        mMap.clear();
        for (int i = 0; i <listPlaces.size() ; i++) {

            Log.d(TAG, "getClosePlaces: "+listPlaces.get(i).getPlaceName());

            mMap.addMarker(new MarkerOptions().position(listPlaces.get(i).getLatLngLocation()).title(listPlaces.get(i).getPlaceName())
            .snippet(listPlaces.get(i).getPlaceAddress()));
        }
        layoutManager=new LinearLayoutManager(this);
        recyclerAdapter=new RecyclerAdapter(listPlaces,mMap);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(itemAnim);
        recycler.setAdapter(recyclerAdapter);
        //this iw here recycler view will be implemented
    }
}
