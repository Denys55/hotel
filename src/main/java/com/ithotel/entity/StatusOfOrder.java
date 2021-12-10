package com.ithotel.entity;

import java.io.Serializable;

public enum StatusOfOrder implements Serializable {
    NEW (1, "new"),
    ON_CONFIRMATION (2,"on confirmation"),
    INVOICED (3, "invoiced"),
    PAID (4, "paid"),
    CANCELED (5, "canceled");

    private final int id;
    private final String title;

    StatusOfOrder(int id, String title){
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
