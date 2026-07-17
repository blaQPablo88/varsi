package com.varsi.common.dto;

public class ApiResponses<T> {

    private boolean succes;
    private String message;
    private T data;

    public ApiResponses(boolean succes, String message, T data) {
        this.succes = succes;
        this.message = message;
        this.data = data;
    }

    public void setSucces(boolean succes) { this.succes = succes; }

    public void setMessage(String message) { this.message = message; }

    public void setData(T data) { this.data = data; }
}
