package com.sumwinsun.activity.exception;

/**
 * 活动异常
 * Created by Administrator on 2016/9/19 0019.
 */
public class ActivityException extends RuntimeException {
    public ActivityException() {
        super();
    }

    public ActivityException(String message) {
        super(message);
    }

    public ActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}
