package com.worldline.workshop.core.interactor.sample;

import com.worldline.workshop.core.data.DataSource;
import com.worldline.workshop.core.interactor.FutureInteractor;
import com.worldline.workshop.core.interactor.UiThread;
import com.worldline.workshop.points.bean.PointsOfInterest;

/**
 * SampleInteractor
 */

public class SampleInteractor extends FutureInteractor<PointsOfInterest> {

    public SampleInteractor(DataSource<PointsOfInterest> dataSource, UiThread uiThread) {
        super(dataSource, uiThread);
    }

}
