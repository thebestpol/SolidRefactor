package com.worldline.workshop.core.data;

/**
 * DataSource
 */

public interface DataSource <T> {

    public T getData() throws Exception;

}
