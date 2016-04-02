package edu.gvsu.cis.zimmecas.barhopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class settings extends AppCompatActivity implements View.OnClickListener {

    TextView bodyProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bodyProfile = (TextView) findViewById(R.id.bodyProfileView);
        bodyProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==bodyProfile) {
            Intent start = new Intent(this, BodyProfile.class);
            startActivity(start);
        }
    }
}
