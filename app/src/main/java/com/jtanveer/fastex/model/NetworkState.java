package com.jtanveer.fastex.model;

public class NetworkState {

    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS, "Successfully Loaded");
        LOADING = new NetworkState(Status.RUNNING, "Loading...");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

}
