package edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gvsu.cis.zimmecas.barhopper.Bar;
import edu.gvsu.cis.zimmecas.barhopper.MainActivity;
import edu.gvsu.cis.zimmecas.barhopper.R;
import edu.gvsu.cis.zimmecas.barhopper.barsRecyclerView.dummy.DummyContent;

/**
 * A fragment representing a single Bar detail screen.
 * This fragment is either contained in a {@link BarListActivity}
 * in two-pane mode (on tablets) or a {@link BarDetailActivity}
 * on handsets.
 */
public class BarDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    /**
     * The dummy content this fragment is presenting.
     */
    private Bar mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("index")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = MainActivity.getBars().get(getArguments().getInt("index", 0));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
                appBarLayout.setCollapsedTitleTextColor(Color.parseColor("blue"));
                appBarLayout.setExpandedTitleColor(Color.parseColor("blue"));
                appBarLayout.setBackgroundColor(Color.parseColor("black"));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bar_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.bar_detail)).setText("Address: "+mItem.getAddress()+
            "\nPhone: "+mItem.getPhoneNumber()+
            "\nWebsite: "+mItem.getHref());
        }

        return rootView;
    }
}
