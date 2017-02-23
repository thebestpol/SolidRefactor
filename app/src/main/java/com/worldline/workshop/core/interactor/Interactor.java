package com.worldline.workshop.core.interactor;

import com.worldline.workshop.core.Callback;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Interactor {

    protected Callback callback;

    public void execute(Callback callback) {
        this.callback = callback;
        run();
    }

    protected abstract void run();

    public abstract void stop();


    protected class InteractorThreadFactory implements ThreadFactory {
        private static final String THREAD_PREFIX = "PoolExecutor_";

        private AtomicLong counter = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_PREFIX + counter.getAndIncrement());
        }
    }
}
