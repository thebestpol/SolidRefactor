package com.worldline.workshop.core.interactor;


import android.os.Handler;

public class MainThread implements UiThread {

    private final Handler handler;

    public MainThread() {
        handler = new Handler();
    }

    @Override
    public void run(Runnable runnable) {
        handler.post(runnable);
    }
}
