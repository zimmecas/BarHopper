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
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class settings extends AppCompatActivity implements View.OnClickListener {

    TextView bodyProfile, infoSummary;
    LinearLayout setHomeLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.routeItem:
                Intent start = new Intent(this, ItemListActivity.class);
                startActivity(start);
                return true;
            case R.id.mapItem:
                Intent start2 = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen.class);
                startActivity(start2);
                return true;
            case R.id.bacItem:
                Intent start3 = new Intent(this, BACCalculator.class);
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
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent input = getIntent();
        bodyProfile = (TextView) findViewById(R.id.bodyProfileView);
        bodyProfile.setOnClickListener(this);
        setHomeLayout = (LinearLayout) findViewById(R.id.setHomeLinearLayout);
        setHomeLayout.setOnClickListener(this);
        infoSummary = (TextView) findViewById(R.id.infoSummaryView);
        infoSummary.setOnClickListener(this);
    }

    public static void setGender(Gender g) {
        MainActivity.setGender(g);
    }

    public static void setWeight(int w) {
        MainActivity.setWeight(w);
    }

    public static void setAddress(String a) {
        MainActivity.setAddress(a);
    }

    public static Gender getGender() {
        return MainActivity.getGender();
    }

    public static int getWeight() {
        return MainActivity.getWeight();
    }

    public static String getAddress() {
        return MainActivity.getAddress();
    }
    
    @Override
    public void onClick(View v) {
        if (v==bodyProfile) {
            Intent start = new Intent(this, BodyProfile.class);
            startActivity(start);
        }
        else if (v==setHomeLayout) {
            Intent start = new Intent(this, setHome.class);
            startActivity(start);
        }
        else if (v==infoSummary) {
            Intent start = new Intent(this, viewInfo.class);
            startActivity(start);
        }
    }
}
