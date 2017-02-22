package com.worldline.workshop.core.model;

import com.worldline.workshop.core.Callback;

/**
 * Created by PoL on 22/02/17.
 */

public interface Model<D> {

    void getData(Callback<D> callback);
}
