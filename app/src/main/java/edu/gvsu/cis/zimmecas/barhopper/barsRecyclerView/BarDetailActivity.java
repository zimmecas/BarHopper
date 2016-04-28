package edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import edu.gvsu.cis.zimmecas.barhopper.Bar;
import edu.gvsu.cis.zimmecas.barhopper.MainActivity;
import edu.gvsu.cis.zimmecas.barhopper.R;


/**
 * An activity representing a single Bar detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BarListActivity}.
 */
public class BarDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_action_name);
        fab.setOnClickListener(this);
/*            @Override
            public void onClick(View view) {
                //first bar
                //pub 43
                String url = "http://www.mlive.com/business/west-michigan/index.ssf/2010/04/owner_of_diversions_and_pub_43.html";
                String oprnurl = "" + MainActivity.getCurrentRoute();
                Intent i = new Intent(Intent.ACTION_VIEW);
                //set back to openurl once it works
                i.setData(Uri.parse(url));
                startActivity(i);
            }*/
            //));

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            int index = getIntent().getIntExtra("index", 0);
            bar = MainActivity.getBars().get(index);
            arguments.putInt("index",
                    getIntent().getIntExtra("index", 0));
            BarDetailFragment fragment = new BarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bar_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, BarListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
            //first bar
            //pub 43
            String url = "http://www.mlive.com/business/west-michigan/index.ssf/2010/04/owner_of_diversions_and_pub_43.html";
            String openurl = "" + bar.getHref();
            Intent i = new Intent(Intent.ACTION_VIEW);
            //set back to openurl once it works
            i.setData(Uri.parse(openurl));
            startActivity(i);
    }
}
