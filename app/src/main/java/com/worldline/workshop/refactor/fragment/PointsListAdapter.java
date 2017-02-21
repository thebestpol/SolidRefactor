package com.worldline.workshop.refactor.fragment;

import com.worldline.workshop.refactor.POI;
import com.worldline.workshop.refactor.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * PointsListAdapter
 */

public class PointsListAdapter extends RecyclerView.Adapter {

    private ArrayList<POI> elements;

    private Context context;

    public PointsListAdapter(Context context, ArrayList<POI> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_point, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).loadElement(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private POI poi;

        private TextView itemId;

        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            itemId = ((TextView) itemView.findViewById(R.id.id));
            title = ((TextView) itemView.findViewById(R.id.title));
        }

        public void loadElement(POI poi) {
            this.poi = poi;
            itemId.setText(this.poi.getId());
            title.setText(this.poi.getTitle());
        }
    }
}
