package com.app.play.http;

import com.google.gson.JsonParseException;

/**
 * @author Starry Jerry
 * @since 2017/10/29.
 */

public class NetworkException extends Exception {

    private int errorCode;
    private String errorMessage;
    private static String logFormat = "%d --> %s";

    public NetworkException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public NetworkException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public NetworkException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public static NetworkException newException(int code, String message) {
//        Logger.i(String.format(logFormat, code, message));
        return new NetworkException(code, message);
    }

    public static NetworkException newException(Exception why) {
        if (why instanceof NetworkException) {
            return (NetworkException) why;
        } else if (why instanceof NullPointerException) {
            return newException(Errors.Code.NULL_EXCEPTION, Errors.Message.RESPONSE_PARSE_ERROR);
        } else if (why instanceof JsonParseException) {
            return newException(Errors.Code.RESPONSE_PARSE_ERROR, Errors.Message.RESPONSE_PARSE_ERROR);
        } else {
            return newException(Errors.Code.UNKNOWN_ERROR, Errors.Message.UNKNOWN_ERROR);
        }
    }
}
