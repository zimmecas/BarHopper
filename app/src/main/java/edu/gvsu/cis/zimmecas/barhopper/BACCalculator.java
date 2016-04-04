package edu.gvsu.cis.zimmecas.barhopper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BACCalculator extends AppCompatActivity implements View.OnClickListener {

    TextView summary;
    ImageButton drink, wineDrink, shot, whisky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccalculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        summary = (TextView) findViewById(R.id.Summary);
        drink = (ImageButton) findViewById(R.id.Drink);
        shot= (ImageButton) findViewById(R.id.DSHot);
        whisky=(ImageButton) findViewById(R.id.DWisky);
        drink.setOnClickListener(this);
        shot.setOnClickListener(this);
        whisky.setOnClickListener(this);
        wineDrink = (ImageButton) findViewById(R.id.DWine) ;
        wineDrink.setOnClickListener(this);

        update();
    }

    public float calculateBAC(int drinks, int weight, Gender gender) {
        float BAC;
        float r;
        float c = 454;
        if (gender==Gender.Male) r = 0.68f;
        else r = 0.55f;
        BAC = ((14*drinks)/(c*weight*r))*100;
        BAC = Math.round(BAC*100)/100f;
        //TODO: subtract 0.015 per hour
        return BAC;
    }

    public void update() {
        summary.setText("You have had " + MainActivity.getDrinks() +
                        " drinks.\nYour blood alcohol content (BAC) is: " +
                        calculateBAC(MainActivity.getDrinks(),
                                MainActivity.getWeight(),
                                MainActivity.getGender()) + ".");
        //TODO: add more information about the user's BAC level.
        //Example: how their functionality is inhibited
    }

    @Override
    public void onClick(View v) {
        MainActivity.addDrink();
        update();
    }
}
