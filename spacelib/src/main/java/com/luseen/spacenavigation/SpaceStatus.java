package com.luseen.spacenavigation;

/**
 * Created by Chatikyan on 12.08.2016-23:13.
 */

public enum SpaceStatus {

    NOT_DEFINED(-1),
    DEFINED(1);

    private int status;

    SpaceStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
