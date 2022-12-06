package com.csp.spring.web.model;

/**
 * Error response body
 * <p>
 * {"error": {"type": "", "message": ""}}
 *
 * @author chensiping
 * @since 2019-08-14
 **/
public class ErrorResponse {

    private ErrorModel error;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorModel error) {
        this.error = error;
    }

    public ErrorResponse(String type, String message) {
        error = new ErrorModel(type, message);
    }

    public static ErrorResponse build(String type, String message) {
        return new ErrorResponse(type, message);
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }

    public ErrorModel getError() {
        return error;
    }
}
