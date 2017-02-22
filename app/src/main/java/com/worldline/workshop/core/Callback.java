package com.worldline.workshop.core;

/**
 * Created by PoL on 22/02/17.
 */

public interface Callback<D> {

    void ok(D data);


    void ko(Throwable throwable);
}
