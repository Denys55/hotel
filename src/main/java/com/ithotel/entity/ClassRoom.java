package com.ithotel.entity;

import java.io.Serializable;

public enum ClassRoom implements Serializable {
    ECONOMY (1,"economy"),
    STANDARD (2, "standard"),
    LUX (3, "lux");

    private final int id;
    private final String title;

    ClassRoom(int id, String title){
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
}
