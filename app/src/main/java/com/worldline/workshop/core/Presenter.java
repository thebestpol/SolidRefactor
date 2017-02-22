package com.worldline.workshop.core;

import com.worldline.workshop.core.entity.ViewEntity;
import com.worldline.workshop.core.model.Model;
import com.worldline.workshop.core.view.ContentView;

/**
 * Created by PoL on 22/02/17.
 */

public abstract class Presenter<M extends Model, V extends ContentView<? extends ViewEntity>> {

    protected final V view;
    protected final M model;

    public Presenter(V view, M model) {
        this.view = view;
        this.model = model;
    }

    public abstract void start();

    public abstract void stop();

}
