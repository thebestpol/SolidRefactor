package com.worldline.workshop.core.view;

/**
 * ContentView
 */

public interface ContentView<C> {

    void showProgress();

    void hideProgress();

    void showError();

    void loadContent(C content);

}
