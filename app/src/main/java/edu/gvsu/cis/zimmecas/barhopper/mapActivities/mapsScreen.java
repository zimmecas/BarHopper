package edu.gvsu.cis.zimmecas.barhopper.mapActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gvsu.cis.zimmecas.barhopper.R;

public class mapsScreen extends AppCompatActivity {

    GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        onMapReady(myMap);
    }

    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        // Add a marker in Grand Rapids, USA,  and move the camera.
        LatLng grandRapids = new LatLng(42.9634, 85.6681);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(grandRapids));
    }
}
