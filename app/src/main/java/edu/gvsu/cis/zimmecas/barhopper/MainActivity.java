package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button map, route, settings, bac;
    static AppInfo info;

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


    @Override
    public void onClick(View v) {
        if (v==map) {
            Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.mapsScreen.class);
            startActivity(start);
        }
        else if (v==route) {

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
