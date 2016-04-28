package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BACCalculator extends AppCompatActivity implements View.OnClickListener {

    TextView summary;
    ImageButton drink;
    float BAC;
    Timer timer;
    Button reset;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bac_toolbar,menu);
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
            case R.id.mapItem:
                Intent start3 = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen.class);
                startActivity(start3);
                return true;
            case R.id.homeItem:
                Intent start4 = new Intent(this, MainActivity.class);
                startActivity(start4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccalculator);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        summary = (TextView) findViewById(R.id.Summary);
        drink = (ImageButton) findViewById(R.id.Drink);
        drink.setOnClickListener(this);
        reset = (Button) findViewById(R.id.resetButton);
        reset.setOnClickListener(this);

        update();
/*
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 60000, 60000);*/
    }

    public long calculateTime() {
        Date now = new Date();
        long time = (now.getTime()-MainActivity.getStartTime())/1000;
        return time;
    }

    public void calculateBAC() {
        int drinks = MainActivity.getDrinks();
        int weight = MainActivity.getWeight();
        Gender gender = MainActivity.getGender();
        float r;
        float c = 454;
        if (gender==Gender.Male) r = 0.68f;
        else r = 0.55f;
        BAC = ((14*drinks)/(c*weight*r))*100;
        //subtract 0.015 per hour
        BAC = BAC - ((((float) calculateTime())/3600)*0.015f);
        BAC = Math.round(BAC*100)/100f;
        if (BAC<=0) {
            MainActivity.reset();
            BAC = 0;
        }
    }

    public String timeToString() {
        String s = "";
        long time = calculateTime();
        int hours = ((int) time) / 3600;
        int minutes = (((int) time) % 3600) / 60;
        if (hours == 1) {
            s += hours + " hour";
            if (minutes>=1) { s += " and "; }
        }
        else if (hours > 1) {
            s += hours + " hours";
            if (minutes>=1) { s += " and "; }
        }
        if (minutes == 1) {
            s += minutes + " minute";
        }
        else {
            s += minutes + " minutes";
        }
        return s;
    }

    public void update() {
        calculateBAC();
        summary.setText("You have had " + MainActivity.getDrinks() +
                        " drinks.\nYou started drinking " + timeToString() +
                        " ago.\n" + "Your blood alcohol content (BAC) is: " +
                        BAC + ".");
        //TODO: add more information about the user's BAC level.
        //Example: how their functionality is inhibited
    }

    @Override
    public void onClick(View v) {
        if (v==drink) {
            MainActivity.addDrink();
            update();
        }
        else if (v==reset) {
            MainActivity.reset();
            update();
        }
    }
}
