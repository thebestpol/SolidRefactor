package com.worldline.workshop.points.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.worldline.workshop.points.MainActivity;
import com.worldline.workshop.points.R;
import com.worldline.workshop.points.adapter.PointOfInterestAdapter;
import com.worldline.workshop.points.bean.PointOfInterest;
import com.worldline.workshop.points.bean.PointsOfInterest;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * PointsListFragment
 */
public class PointsListFragment extends Fragment {

    private RecyclerView recyclerView;

    private View progress;

    public static PointsListFragment newInstance() {
        return new PointsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_points_list, container, false);

        recyclerView = ((RecyclerView) fragmentView.findViewById(R.id.recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progress = fragmentView.findViewById(R.id.progress);

        new AsyncTask<Void, Void, ArrayList<PointOfInterest>>() {

            @Override
            protected void onPreExecute() {
                progress.setVisibility(View.VISIBLE);

                super.onPreExecute();
            }

            @Override
            protected ArrayList<PointOfInterest> doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://t21services.herokuapp.com/points")
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        PointsOfInterest serviceResponse = new Gson()
                                .fromJson(response.body().charStream(), PointsOfInterest.class);

                        return serviceResponse.getList();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<PointOfInterest> objects) {
                progress.setVisibility(View.GONE);

                PointOfInterestAdapter pointOfInterestAdapter = new PointOfInterestAdapter(PointsListFragment.this, objects);
                recyclerView.setAdapter(pointOfInterestAdapter);

                super.onPostExecute(objects);
            }
        }.execute();

        return fragmentView;
    }

    public void onPointOfInterestClicked(PointOfInterest pointOfInterest) {
        MainActivity activity = (MainActivity) getActivity();
        activity.replaceFragment(PointOfInterestDetailFragment.newInstance(pointOfInterest.getId()));
    }
}
