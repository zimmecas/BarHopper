package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button map, route, settings, bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = (Button) findViewById(R.id.mapButton);
        route = (Button) findViewById(R.id.routesButton);
        settings = (Button) findViewById(R.id.settingsButton);
        bac = (Button) findViewById(R.id.bacButton);
        map.setOnClickListener(this);
        route.setOnClickListener(this);
        settings.setOnClickListener(this);
        bac.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==map) {

        }
        else if (v==route) {

        }
        else if (v==settings) {
            Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.settings.class);
            startActivity(start);
        }
        else if (v==bac) {

        }
    }
}
