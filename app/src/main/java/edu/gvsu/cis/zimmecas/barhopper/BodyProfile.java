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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BodyProfile extends AppCompatActivity implements View.OnClickListener {

    Button submit, cancel;
    RadioGroup gender;
    RadioButton male, female;
    EditText weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        submit = (Button) findViewById(R.id.Save);
        gender = (RadioGroup) findViewById(R.id.Gender);
        male = (RadioButton) findViewById(R.id.Male);
        female = (RadioButton) findViewById(R.id.Female);
        weight = (EditText) findViewById(R.id.Weight);
        cancel = (Button) findViewById(R.id.Cancel);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setGender(Gender g) {
        settings.setGender(g);
    }

    public void setWeight(int w) {
        settings.setWeight(w);
    }

    public Gender getGender() {
        return settings.getGender();
    }

    public int getWeight() {
        return settings.getWeight();
    }

    @Override
    public void onClick(View v) {
        if (v==submit) {
            String w = weight.getText()+"";
            if (w.equals("")) {
                new AlertDialog.Builder(this)
                        .setTitle("Invalid input")
                        .setMessage("Please enter a weight.")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            } else {
                if (gender.getCheckedRadioButtonId() == R.id.Male) {
                    setGender(Gender.Male);
                    setWeight(Integer.parseInt(w));
                    finish();
                } else if (gender.getCheckedRadioButtonId() == R.id.Female) {
                    setGender(Gender.Female);
                    setWeight(Integer.parseInt(w));
                    finish();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Invalid input")
                            .setMessage("Please select your gender.")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            }
        }
        else if (v==cancel){
            finish();
        }
    }
}
