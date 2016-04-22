package edu.gvsu.cis.zimmecas.barhopper;

import android.location.Address;
import android.provider.Settings;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Tim DeVries on 4/1/2016.
 */
public class AppInfo  {
    Gender gender;
    int weight;
    int drinks;
    String address;
    long startTime;
    float BAC;
    ArrayList<Bar> bars;
    ArrayList<Route> routes;

    public AppInfo() {
        gender = Gender.Male;
        weight = 180;
        drinks = 0;
        BAC = 0;
        startTime = new Date().getTime();
    }

    public void setGender(Gender g) {
        gender = g;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public Gender getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }

    public void addDrink() {
        if (drinks == 0) {
            Date date = new Date();
            startTime = date.getTime();
        }
        drinks += 1;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setAddress(String a) {
        address = a;
    }

    public String getAddress() {
        return address;
    }

    public void setDrinks(int d) {
        drinks = d;
    }

    public void setStartTime(long s) {
        startTime = s;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setBAC(float bac) {
        BAC = bac;
    }

    public float getBAC() {
        return BAC;
    }

    public void reset() {
        drinks = 0;
        BAC = 0;
        startTime = new Date().getTime();
    }

    public void setBars(ArrayList<Bar> arr) {
        bars = arr;
        setDefaultRoutes();
    }

    public void setDefaultRoutes() {
        Random rand = new Random();
        for (int i = 1; i<5; i++) {
            Route nr = new Route("Route "+i);
            nr.addBar(bars.get(rand.nextInt(100)));
            nr.addBar(bars.get(rand.nextInt(100)));
            nr.addBar(bars.get(rand.nextInt(100)));
            nr.addBar(bars.get(rand.nextInt(100)));
            routes.add(nr);
        }
    }

}
