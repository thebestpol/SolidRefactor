package com.worldline.workshop.points.adapter;

import com.worldline.workshop.points.R;
import com.worldline.workshop.points.bean.PointOfInterest;
import com.worldline.workshop.points.fragment.PointsListFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * PointOfInterestAdapter
 */
// FIXME Generics not used correctly
public class PointOfInterestAdapter extends RecyclerView.Adapter {

    // FIxme always use abstactions
    private ArrayList<PointOfInterest> elements;

    private PointsListFragment context;

    // FIXME waaaat
    public PointOfInterestAdapter(PointsListFragment context, ArrayList<PointOfInterest> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //FIXME WAT?
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

    // FIXME inner or nested?
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
                    // FIXME WAT??
                    context.onPointOfInterestClicked(pointOfInterest);
                }
            });
        }

        public void loadElement(PointOfInterest pointOfInterest) {
            this.pointOfInterest = pointOfInterest;
            //FIXME this should be used only when is needed
            itemId.setText(this.pointOfInterest.getId());
            title.setText(this.pointOfInterest.getTitle());
        }
    }
}
