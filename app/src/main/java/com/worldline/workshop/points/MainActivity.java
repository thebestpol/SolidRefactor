package com.worldline.workshop.points;
// FIXME Must be support library

import com.worldline.workshop.points.fragment.PointsListFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * MainActivity
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // FIXME Must check if activity is restored or not
        PointsListFragment fragment = PointsListFragment.newInstance();
        addFragment(fragment);
    }

    // FIXME It's not the activity's responsability
    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // FIXME Container id hardcoded, should be open to modification
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    // FIXME It's not the activity's responsability
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // FIXME Animations hardcoded, should be open to modification
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
