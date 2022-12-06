package com.csp.spring.web.model;

/**
 * @author chensiping
 * @since 2019-08-20
 **/
public class ErrorModel {

    private String type;

    private String message;

    public ErrorModel() {
    }

    public ErrorModel(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
