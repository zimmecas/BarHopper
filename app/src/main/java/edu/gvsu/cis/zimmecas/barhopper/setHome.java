package edu.gvsu.cis.zimmecas.barhopper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setHome extends AppCompatActivity implements View.OnClickListener {

    Button cancel, save;
    EditText address;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cancel = (Button) findViewById(R.id.cancelButton);
        save = (Button) findViewById(R.id.saveButton);
        address = (EditText) findViewById(R.id.addressEditText);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void setGender(Gender g) {
        settings.setGender(g);
    }

    public static void setWeight(int w) {
        settings.setWeight(w);
    }

    public static void setAddress(String a) {
        settings.setAddress(a);
    }

    public static Gender getGender() {
        return settings.getGender();
    }

    public static int getWeight() {
        return settings.getWeight();
    }

    public static String getAddress() {
        return settings.getAddress();
    }


    @Override
    public void onClick(View v) {
        if (v==cancel) {
            finish();
        }
        else if (v==save) {
            if (address.getText()+""=="") {
                new AlertDialog.Builder(this)
                        .setTitle("Invalid input")
                        .setMessage("Please enter a valid address.")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
            else {
                setAddress(address.getText().toString());
                finish();
            }
        }
    }
}
