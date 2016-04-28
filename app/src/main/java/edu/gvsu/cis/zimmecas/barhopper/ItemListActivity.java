package edu.gvsu.cis.zimmecas.barhopper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import android.view.MenuItem;

import edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView.BarListActivity;

import java.util.List;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    View recyclerView;
    private String s = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.route_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mapItem:
                Intent start = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.mapActivities.mapsScreen.class);
                startActivity(start);
                return true;
            case R.id.setItem:
                Intent start2 = new Intent(this, edu.gvsu.cis.zimmecas.barhopper.settings.class);
                startActivity(start2);
                return true;
            case R.id.bacItem:
                Intent start3 = new Intent(this, BACCalculator.class);
                startActivity(start3);
                return true;/*
            case R.id.homeItem:
                navigateUpFromSameTask(this);
                //Intent start4 = new Intent(this, MainActivity.class);
                //startActivity(start4);
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttachedToWindow() {
        setupRecyclerView((RecyclerView) recyclerView);
        super.onAttachedToWindow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        //setupRecyclerView((RecyclerView) recyclerView);



        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This ID represents the Home or Up button. In the case of this
//            // activity, the Up button is shown. Use NavUtils to allow users
//            // to navigate up one level in the application structure. For
//            // more details, see the Navigation pattern on Android Design:
//            //
//            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
//            //
//            navigateUpFromSameTask(this);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(MainActivity.getRoutes()));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<Route> mValues;

        public SimpleItemRecyclerViewAdapter(List<Route> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (position<getItemCount()-1) {
                holder.mItem = mValues.get(position);
                holder.mIdView.setText(mValues.get(position).route.size() + "\nBars");
                holder.mIdView.setTextSize(14);
                holder.mIdView.setTypeface(null, Typeface.NORMAL);
                holder.mIdView.setPadding(5, 5, 5, 5);
                holder.mContentView.setText(mValues.get(position).name);
                holder.mContentView.setTextSize(25);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BarListActivity.class);
                        intent.putExtra("index", MainActivity.getRoutes().indexOf(holder.mItem));
                        context.startActivity(intent);
                        /*if (mTwoPane) {
                            Bundle arguments = new Bundle();
                            arguments.putInt("index", MainActivity.getRoutes().indexOf(holder.mItem));
                            ItemDetailFragment fragment = new ItemDetailFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();
                        } else {
                            Context context = v.getContext();
                            Intent intent = new Intent(context, ItemDetailActivity.class);
                            intent.putExtra("index", MainActivity.getRoutes().indexOf(holder.mItem));

                            context.startActivity(intent);
                        }*/
                    }
                });
            }
            else {
                //holder.mItem = mValues.get(position);
                holder.mIdView.setText("+");
                holder.mIdView.setTextSize(35);
                holder.mIdView.setTypeface(null, Typeface.BOLD);
                holder.mIdView.setPadding(10,0,10,10);

                holder.mContentView.setText("Create new route");
                holder.mContentView.setTextSize(25);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: open createRoute Activity
                        final Context context = v.getContext();
                        final EditText editText = new EditText(context);
                        editText.setInputType(InputType.TYPE_CLASS_TEXT);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Name your route");
                        builder.setView(editText);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                s = editText.getText().toString();
                                MainActivity.addNewRoute(s);
                                Intent intent = new Intent(context, BarListActivity.class);
                                intent.putExtra("index", position);
                                context.startActivity(intent);

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();


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
            public Route mItem;

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
