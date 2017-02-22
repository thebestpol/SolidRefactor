package com.worldline.workshop.points.bean;

import java.util.ArrayList;

/**
 * PointsOfInterest
 */

public class PointsOfInterest {

    // FIXME Always use abstractions, Never initialize for free
    private ArrayList<PointOfInterest> list = new ArrayList<>();

    public PointsOfInterest() {

    }

    public ArrayList<PointOfInterest> getList() {
        return list;
    }

    public void setList(ArrayList<PointOfInterest> list) {
        this.list = list;
    }
}
