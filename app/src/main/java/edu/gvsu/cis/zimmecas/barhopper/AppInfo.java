package edu.gvsu.cis.zimmecas.barhopper;

import android.provider.Settings;

import java.sql.Time;

/**
 * Created by Tim DeVries on 4/1/2016.
 */
public class AppInfo  {
    Gender gender;
    int weight;
    int drinks;

    public AppInfo() {
        gender = Gender.Male;
        weight = 180;
        drinks = 0;
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
        drinks += 1;
    }

    public int getDrinks() {
        return drinks;
    }


}
