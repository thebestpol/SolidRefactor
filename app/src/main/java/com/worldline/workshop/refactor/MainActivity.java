package com.worldline.workshop.refactor;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * MainActivity
 */

public class MainActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));

        new AsyncTask<Void, Void, ArrayList<POI>>() {

            @Override
            protected ArrayList<POI> doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://t21services.herokuapp.com/points");

                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    String jsonString = new String(buffer, "UTF-8");

                    ServiceResponse serviceResponse = new Gson().fromJson(jsonString, ServiceResponse.class);

                    return serviceResponse.getList();

                } catch (Exception e) {
                }

                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<POI> objects) {
                super.onPostExecute(objects);

                // TODO sort list and load it

            }
        }.execute();
    }
}
