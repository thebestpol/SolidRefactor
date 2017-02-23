package com.worldline.workshop.core.interactor;

import com.worldline.workshop.core.data.DataSource;
import com.worldline.workshop.core.exception.InteractorException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FutureInteractor
 */

public class FutureInteractor <D> extends Interactor {

    private final ExecutorService executorService;

    private UiThread uiThread;

    private DataSource<D> dataSource;

    private Future<?> future;

    public FutureInteractor(DataSource<D> dataSource, UiThread uiThread) {
        this.dataSource = dataSource;
        this.uiThread = uiThread;

        executorService = Executors.newSingleThreadScheduledExecutor(new InteractorThreadFactory());
    }


    @Override
    protected void run() {
        future = executorService.submit(new FutureRunnable<D>() {
            @Override
            D processData() throws Exception {
                return dataSource.getData();
            }
        });
    }

    @Override
    public void stop() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
        }
    }

    abstract class FutureRunnable <D> implements Runnable {

        abstract D processData() throws Exception;

        @Override
        public void run() {
            D data = null;
            try {
                data = processData();

            } catch (Exception e) {
                uiThread.run(new Runnable() {
                    @Override
                    public void run() {
                        callback.ko(new InteractorException());
                    }
                });
            } finally {
                if (data != null) {
                    final Object auxData = data;
                    uiThread.run(new Runnable() {
                        @Override
                        public void run() {
                            callback.ok(auxData);
                        }
                    });
                }
            }
        }
    }
}
