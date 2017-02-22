package com.worldline.workshop.points.fragment;

import com.google.gson.Gson;

import com.worldline.workshop.points.R;
import com.worldline.workshop.points.bean.PointOfInterest;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PointOfInterestDetailFragment extends Fragment {

    private static final String KEY_POINT_ID = "point_id";

    private TextView pointTitle;

    private TextView pointDescription;

    private TextView pointTransport;

    private TextView pointEmail;

    private TextView pointPhone;

    private View progress;

    private String pointId;

    public static PointOfInterestDetailFragment newInstance(String pointId) {
        Bundle args = new Bundle();
        args.putString(KEY_POINT_ID, pointId);

        PointOfInterestDetailFragment fragment = new PointOfInterestDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_point_detail, container, false);

        pointTitle = ((TextView) view.findViewById(R.id.pointTitle));
        pointDescription = ((TextView) view.findViewById(R.id.pointDescription));
        pointTransport = ((TextView) view.findViewById(R.id.pointTransport));
        pointEmail = ((TextView) view.findViewById(R.id.pointEmail));
        pointPhone = ((TextView) view.findViewById(R.id.pointPhone));

        progress = view.findViewById(R.id.progress);

        return view;
    }

    // FIXME Override annotation
    public void onResume() {
        super.onResume();

        Bundle arguments = getArguments();
        pointId = arguments.getString(KEY_POINT_ID);

        fetchPointDetails();
    }

    private void fetchPointDetails() {
        new AsyncTask<Void, Void, PointOfInterest>() {
            @Override
            protected PointOfInterest doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://t21services.herokuapp.com/points/" + pointId)
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        PointOfInterest serviceResponse = new Gson()
                                .fromJson(response.body().charStream(), PointOfInterest.class);

                        return serviceResponse;
                    }

                } catch (Exception e) {
                    // FIXME errors must be handled
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(PointOfInterest pointOfInterest) {
                super.onPostExecute(pointOfInterest);

                progress.setVisibility(View.GONE);

                loadPointOfInterest(pointOfInterest);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progress.setVisibility(View.VISIBLE);
            }
        }.execute();
    }

    // FIXME It's not responsibility of this class
    private SpannableStringBuilder buildBoldLabelString(String label) {
        int x = label.indexOf(':');
        SpannableStringBuilder s = new SpannableStringBuilder(label);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, x, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return s;
    }

    private void loadPointOfInterest(PointOfInterest pointOfInterest) {
        pointTitle.setText(buildBoldLabelString("Title: \n" + pointOfInterest.getTitle()));
        pointDescription.setText(buildBoldLabelString("Description: \n"
                + pointOfInterest.getDescription()));
        pointEmail.setText(buildBoldLabelString("Email: \n" + pointOfInterest.getEmail()));
        pointPhone.setText(buildBoldLabelString("Phone: \n" + pointOfInterest.getPhone()));
        pointTransport.setText(buildBoldLabelString("Available transport: \n"
                + pointOfInterest.getTransport()));
    }
}
