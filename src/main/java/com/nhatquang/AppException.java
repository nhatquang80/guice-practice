package com.nhatquang;

/**
 * @author Quang Nguyen
 */
public class AppException extends Exception {

    public AppException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AppException(Throwable throwable) {
        super(throwable);
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }

    public AppException() {
    }
}
