package edu.gvsu.cis.zimmecas.barhopper.mapActivities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import edu.gvsu.cis.zimmecas.barhopper.BACCalculator;
import edu.gvsu.cis.zimmecas.barhopper.Bar;
import edu.gvsu.cis.zimmecas.barhopper.MainActivity;
import edu.gvsu.cis.zimmecas.barhopper.R;
import edu.gvsu.cis.zimmecas.barhopper.Route;

public class mapsScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    GoogleApiClient mGoogleApiClient;

    GoogleMap myMap;
//    GoogleMap liteMap;

//    SupportMapFragment liteMapFragment;
    SupportMapFragment mMapFragment;
    boolean liteMode = false;
    LocationManager locationManager;
    
    private View mLayout;

    private static final int REQUEST_LOCATION = 0;
    private static String[] PERMISSIONS_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private Route currentRoute;
    static final LatLng GRAND_RAPIDS = new LatLng(42.9634, 85.6681);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.routeItem:
                Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.ItemListActivity.class);
                startActivity(start);
                return true;
            case R.id.setItem:
                Intent start2 = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.settings.class);
                startActivity(start2);
                return true;
            case R.id.bacItem:
                Intent start3 = new Intent(this, BACCalculator.class);
                startActivity(start3);
                return true;/*
            case R.id.homeItem:
                Intent start4 = new Intent(this, MainActivity.class);
                startActivity(start4);
                return true;*/
//            case R.id.switchItem:
//                switchModes();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_maps_screen);
        mLayout = findViewById(R.id.content_maps_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        currentRoute = MainActivity.getCurrentRoute();
        //populateMap(); called in onMapReady

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mapsScreen.this,
                    Manifest.permission.READ_CONTACTS)) {
                Snackbar.make(mLayout, R.string.permission_location_rationale,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat
                                        .requestPermissions(mapsScreen.this, PERMISSIONS_LOCATION,
                                                REQUEST_LOCATION);
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, PERMISSIONS_LOCATION, REQUEST_LOCATION);
            }
        }
        myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        myMap.setMyLocationEnabled(true);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

//        liteMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.liteMap)).getMap();
//        liteMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.liteMap));
//        liteMapFragment.getMapAsync(this);
//        liteMapFragment.setMenuVisibility(false);

        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GRAND_RAPIDS, 15));
        myMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

    private void populateMap() {
        Marker currentBarMarker;
        LatLng latLng;
        if (currentRoute != null){
            for (Bar b : currentRoute.getRoute()) {
                latLng = getLocationFromAddress(getApplicationContext(), b.getAddress());
                if (latLng != null) {
                    System.out.println(latLng.latitude + "from populate");
                    System.out.println(latLng.longitude + "from populate");
                }
                try {
                    currentBarMarker = myMap.addMarker(new MarkerOptions().position(latLng)
                            .draggable(false)
                            .title(b.getName())
                            .snippet(b.getAddress() + "/n" + b.getPhoneNumber()));

                } catch (IllegalArgumentException e){
                    e.printStackTrace();
                    Snackbar.make(mLayout, R.string.marker_not_placed, Snackbar.LENGTH_LONG).show();
                }
            }
        } else {
            return;
        }
    }


    public static LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        if (p1 != null) {
            System.out.println(p1.latitude + "from gLFA");
            System.out.print(p1.longitude + "from gLFA");
        }
        return p1;
    }

//    public void switchModes() {
//        if (liteMode == false) {
//            liteMode = true;
//            mMapFragment.setMenuVisibility(false);
//            liteMapFragment.setMenuVisibility(true);
//        } else if (liteMode == true) {
//            liteMode = false;
//            mMapFragment.setMenuVisibility(true);
//            liteMapFragment.setMenuVisibility(false);
//        }
//    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GRAND_RAPIDS, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        populateMap();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission WAS granted
            } else {
                //permission was NOT granted
            }
        }
    }
}