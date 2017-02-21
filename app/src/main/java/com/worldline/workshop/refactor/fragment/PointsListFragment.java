package com.worldline.workshop.refactor.fragment;

import com.google.gson.Gson;

import com.worldline.workshop.refactor.POI;
import com.worldline.workshop.refactor.R;
import com.worldline.workshop.refactor.ServiceResponse;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        new AsyncTask<Void, Void, ArrayList<POI>>() {

            @Override
            protected void onPreExecute() {
                progress.setVisibility(View.VISIBLE);

                super.onPreExecute();
            }

            @Override
            protected ArrayList<POI> doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://t21services.herokuapp.com/points")
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        ServiceResponse serviceResponse = new Gson()
                                .fromJson(response.body().charStream(), ServiceResponse.class);

                        return serviceResponse.getList();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<POI> objects) {
                progress.setVisibility(View.GONE);

                PointsListAdapter pointsListAdapter = new PointsListAdapter(getActivity(), objects);
                recyclerView.setAdapter(pointsListAdapter);

                super.onPostExecute(objects);
            }
        }.execute();

        return fragmentView;
    }
}
