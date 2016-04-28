package edu.gvsu.cis.zimmecas.barhopper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class viewInfo extends AppCompatActivity {

    TextView gender, weight, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gender = (TextView) findViewById(R.id.GenderText);
        weight = (TextView) findViewById(R.id.WeightText);
        address = (TextView) findViewById(R.id.AddressText);
        if (settings.getGender()==Gender.Male) gender.setText("Male");
        else gender.setText("Female");
        weight.setText(settings.getWeight()+"");
        address.setText(settings.getAddress()+"\nCurrent Route: "+MainActivity.getCurrentRoute().name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
