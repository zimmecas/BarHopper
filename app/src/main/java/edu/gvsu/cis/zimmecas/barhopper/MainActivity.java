package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.UberSdk;

import java.util.ArrayList;

import edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button map, route, settings, bac;
    static AppInfo info;
    SharedPreferences prefs;
    static boolean firstRun = true;
    RideRequestButton requestRide;
    LinearLayout LLVerticle;
    RideParameters rideParams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is necessary for the SDK to function.
        UberSdk.initialize(this, "p3aZ1Qu7bFUtxTPteWfISO5_WT6X3YF-");
        // This is necessary if you'll be using the SDK for implicit grant
        //UberSdk.setRedirectUri("YOUR_REDIRECT_URI");
        // This is useful for testing your application in the sandbox environment
        UberSdk.setSandboxMode(true);
        // China based apps should specify the region
        UberSdk.setRegion(UberSdk.Region.WORLD);

        LLVerticle = (LinearLayout) findViewById(R.id.vertLayout);
        requestRide  = new RideRequestButton(this);
        LLVerticle.addView(requestRide);
        requestRide.setText("Ride home with Uber");
        //rideParams = new RideParameters.Builder().setDropoffLocation()


        map = (Button) findViewById(R.id.mapButton);
        route = (Button) findViewById(R.id.routesButton);
        settings = (Button) findViewById(R.id.settingsButton);
        bac = (Button) findViewById(R.id.bacButton);
        map.setOnClickListener(this);
        route.setOnClickListener(this);
        settings.setOnClickListener(this);
        bac.setOnClickListener(this);

        info = new AppInfo();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains("gender")) {
            if (prefs.getInt("gender", 0) == 0) setGender(Gender.Male);
            else setGender(Gender.Female);
            setWeight(prefs.getInt("weight", 0));
            setAddress(prefs.getString("address", ""));
            setDrinks(prefs.getInt("drinks", 0));
            setStartTime(prefs.getLong("startTime", 0));
        }

        if (firstRun) {
            String page1 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/1";
            String page2 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/2";
            String page3 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/3";
            String page4 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/4";
            String page5 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/5";
            String page6 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/6";
            String page7 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/7";
            String page8 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/8";
            String page9 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/9";
            String page10 = "http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/10";

            new getBarsTask(MainActivity.this).execute(page1, page2, page3, page4, page5, page6, page7, page8, page9, page10);
            firstRun = false;
        }
        //else setDefaultRoutes();
    }

/*    public double getLat(String address) {

    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void setGender(Gender g) {
        info.setGender(g);
    }

    public static void setWeight(int w) {
        info.setWeight(w);
    }

    public static Gender getGender() {
        return info.getGender();
    }

    public static int getWeight() {
        return info.getWeight();
    }

    public static int getDrinks() {
        return info.getDrinks();
    }

    public static void addDrink() {
        info.addDrink();
    }

    public static void setDrinks(int drinks) {
        info.setDrinks(drinks);
    }

    public static void setAddress(String a) {
        info.setAddress(a);
    }

    public static String getAddress() {
        return info.getAddress();
    }

    public static void setStartTime(long startTime) {
        info.setStartTime(startTime);
    }

    public static long getStartTime() {
        return info.getStartTime();
    }

    public static void reset() {
        info.reset();
    }

    public static void setBars(ArrayList<Bar> arr) {
        info.setBars(arr);
    }

    public static ArrayList<Bar> getBars() {
        return info.bars;
    }

    public static ArrayList<Route> getRoutes() {
        return info.getRoutes();
    }

    public void setUberParams() {
        RideParameters rideParams = new RideParameters.Builder()
                .setDropoffLocation(37.795079, -122.4397805, "Home", getAddress())
                .build();
        requestRide.setRideParameters(rideParams);
    }

    public static void setRoute(Route r) { info.setCurrentRoute(r); }

    public static Route getCurrentRoute() {
        return info.getCurrentRoute();
    }

    public static void setDefaultRoutes() { info.setDefaultRoutes(); }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("gender", getGender().ordinal());
        prefsEditor.putInt("weight", getWeight());
        prefsEditor.putString("address", getAddress());
        prefsEditor.putInt("drinks", getDrinks());
        prefsEditor.putLong("startTime",getStartTime());

        prefsEditor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("gender", getGender().ordinal());
        prefsEditor.putInt("weight", getWeight());
        prefsEditor.putString("address", getAddress());
        prefsEditor.putInt("drinks", getDrinks());
        prefsEditor.putLong("startTime",getStartTime());
        prefsEditor.commit();
    }

    @Override
    public void onClick(View v) {
        if (v==map) {
            Intent start = new Intent(this, mapsScreen.class);
            startActivity(start);
        }
        else if (v==route) {
            Intent start = new Intent(this, ItemListActivity.class);
            startActivity(start);
        }
        else if (v==settings) {
            Intent start = new Intent(this, settings.class);
            startActivity(start);
        }
        else if (v==bac) {
            Intent start = new Intent(this, BACCalculator.class);
            startActivity(start);
        }
    }
}
