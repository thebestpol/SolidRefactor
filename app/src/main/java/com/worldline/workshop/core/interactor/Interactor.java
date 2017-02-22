package com.worldline.workshop.core.interactor;

import com.worldline.workshop.core.Callback;

/**
 * Created by PoL on 22/02/17.
 */

public abstract class Interactor {

    protected Callback callback;

    public void execute(Callback callback) {
        this.callback = callback;

        buildTask().execute();
    }

    protected abstract Task buildTask();


    interface Task {
        void execute();
    }

}
