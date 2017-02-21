package com.worldline.workshop.refactor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.worldline.workshop.refactor.R;
import com.worldline.workshop.refactor.bean.PointOfInterest;
import com.worldline.workshop.refactor.fragment.PointsListFragment;

import java.util.ArrayList;

/**
 * PointOfInterestAdapter
 */

public class PointOfInterestAdapter extends RecyclerView.Adapter {

    private ArrayList<PointOfInterest> elements;

    private PointsListFragment context;

    public PointOfInterestAdapter(PointsListFragment context, ArrayList<PointOfInterest> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = this.context.getActivity();
        return new PointOfInterestViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_point, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PointOfInterestViewHolder) holder).loadElement(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    private class PointOfInterestViewHolder extends RecyclerView.ViewHolder {

        private PointOfInterest pointOfInterest;

        private TextView itemId;

        private TextView title;

        public PointOfInterestViewHolder(View itemView) {
            super(itemView);
            itemId = ((TextView) itemView.findViewById(R.id.id));
            title = ((TextView) itemView.findViewById(R.id.title));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onPointOfInterestClicked(pointOfInterest);
                }
            });
        }

        public void loadElement(PointOfInterest pointOfInterest) {
            this.pointOfInterest = pointOfInterest;
            itemId.setText(this.pointOfInterest.getId());
            title.setText(this.pointOfInterest.getTitle());
        }
    }
}
