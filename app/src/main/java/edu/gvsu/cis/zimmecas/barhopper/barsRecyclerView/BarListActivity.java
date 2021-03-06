package edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import edu.gvsu.cis.zimmecas.barhopper.Bar;
import edu.gvsu.cis.zimmecas.barhopper.MainActivity;
import edu.gvsu.cis.zimmecas.barhopper.R;

import edu.gvsu.cis.zimmecas.barhopper.Route;
import edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView.dummy.DummyContent;

import java.util.List;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of Bars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BarListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Route mRoute;
    View recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        toolbar.setBackgroundColor(Color.parseColor("black"));

        recyclerView = findViewById(R.id.bar_list);
        mRoute = MainActivity.getRoutes().get(getIntent().getIntExtra("index", 0));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.route_start);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setRoute(mRoute);
                goToMap(view);
            }
        });
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.bar_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMap(View v){
        Intent start3 = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen.class);
        startActivity(start3);
        finish();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mRoute.getRoute()));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Bar> mValues;

        public SimpleItemRecyclerViewAdapter(List<Bar> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bar_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (position<getItemCount()-1) {
                holder.mItem = mValues.get(position);
                holder.mIdView.setText(position + 1 + "");
                holder.mContentView.setText(mValues.get(position).getName());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BarDetailActivity.class);
                        intent.putExtra("index", MainActivity.getBars().indexOf(holder.mItem));
                        context.startActivity(intent);
                    /*if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(BarDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                        BarDetailFragment fragment = new BarDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bar_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BarDetailActivity.class);
                        intent.putExtra(BarDetailFragment.ARG_ITEM_ID, holder.mItem.getName());

                        context.startActivity(intent);
                    }*/
                    }
                });
            }
            else {
                //holder.mItem = mValues.get(position);
                holder.mIdView.setText("+");
                //holder.mIdView.setTextSize(35);
                holder.mIdView.setTypeface(null, Typeface.BOLD);

                holder.mContentView.setText("Add bar");
                //holder.mContentView.setTextSize(25);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, edu.gvsu.cis.zimmecas.barhopper.AddBar.BarListActivity.class);
                        intent.putExtra("index", MainActivity.getRoutes().indexOf(mRoute));
                        context.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mValues.size()+1;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Bar mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
