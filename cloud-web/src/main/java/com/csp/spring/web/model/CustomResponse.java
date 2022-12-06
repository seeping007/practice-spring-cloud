package com.csp.spring.web.model;

/**
 * @author chensiping
 * @since 2021-08-08
 */
public class CustomResponse<T> {

    private String status;

    private String message;

    private T data;

    public CustomResponse() {
    }

    public CustomResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> CustomResponse<T> ok(String message, T data) {
        return new CustomResponse<>("SUCCESS", message, data);
    }

    public static <T> CustomResponse<T> ok(T data) {
        return new CustomResponse<>("SUCCESS", "", data);
    }

    public static <T> CustomResponse<T> error(String status, String message, T data) {
        return new CustomResponse<>(status, message, data);
    }

    public static <T> CustomResponse<T> error(String status, String message) {
        return new CustomResponse<>(status, message, null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
