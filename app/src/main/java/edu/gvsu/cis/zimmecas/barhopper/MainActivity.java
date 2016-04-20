package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button map, route, settings, bac;
    static AppInfo info;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = new AppInfo();
        map = (Button) findViewById(R.id.mapButton);
        route = (Button) findViewById(R.id.routesButton);
        settings = (Button) findViewById(R.id.settingsButton);
        bac = (Button) findViewById(R.id.bacButton);
        map.setOnClickListener(this);
        route.setOnClickListener(this);
        settings.setOnClickListener(this);
        bac.setOnClickListener(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains("gender")) {
            if (prefs.getInt("gender",0)==0) setGender(Gender.Male);
            else setGender(Gender.Female);
            setWeight(prefs.getInt("weight",0));
            setAddress(prefs.getString("address", ""));
            setDrinks(prefs.getInt("drinks",0));
            setStartTime(prefs.getLong("startTime",0));
        }
    }
    public static void setGender(Gender g) {
        info.setGender(g);
    }


    private ArrayList<Bar> getBars(){
        ArrayList<Bar> barArr = new ArrayList<>();
        Document doc = null;

        for (int i = 1; i < 11; i++) {//runs through variations of URL below, change 11 to # of pages in list.
            try {
                doc = Jsoup.connect("http://businessfinder.mlive.com/MI-Grand-Rapids/Bars-and-Pubs/" + i).get();
                Element innerDetailsSubLeft = doc.select("div.innerDetailsSubLeft").first();//innerDetailsSubLeft is an element containing multiple "resultWrapper" of bars
                Elements resultWrappers = innerDetailsSubLeft.select("div.resultWrapper");//resultWrappers is an ELEMENTS containing every element of <div class="resultWrapper">...</div> inside of innerDetailsSubLeft
            } catch (IOException ex){
                System.out.println("IOException yo");
                ex.printStackTrace();
            }
            /*
               * Ok so this is how it's going to work.
               * each element in resultWrappers has bar information, a name, a phone number, an
               * address, and as well as other things. a nested loop will pull the information from
               * resultWrappers and turn it into a bar object a final loop, nested after the previous
               * loop, will place all those bars into an arraylist and return it
               * I'm sleepy.
               *
               * Theres a possibility we could run this the first time the app starts, then only run
               * it again if bars have been added or removed to/from the list
               * */


        }

        return barArr;
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
            Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.ItemListActivity.class);
            startActivity(start);
        }
        else if (v==settings) {
            Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.settings.class);
            startActivity(start);
        }
        else if (v==bac) {
            Intent start = new Intent(this, BACCalculator.class);
            startActivity(start);
        }
    }
}
