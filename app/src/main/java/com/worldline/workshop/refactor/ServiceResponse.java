package com.worldline.workshop.refactor;

import java.util.ArrayList;

/**
 * ServiceResponse
 */

public class ServiceResponse {

    private ArrayList<POI> list = new ArrayList<>();

    public ServiceResponse() {

    }

    public ArrayList<POI> getList() {
        return list;
    }

    public void setList(ArrayList<POI> list) {
        this.list = list;
    }
}
