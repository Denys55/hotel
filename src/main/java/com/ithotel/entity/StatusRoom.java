package com.ithotel.entity;

import java.io.Serializable;

enum StatusRoom implements Serializable{
    FREE (1, "free"),
    BOOKED (2,"booked"),
    BUSY (3, "busy"),
    NOT_AVAILABLE (4,"not available");
    private final int id;
    private final String title;

    StatusRoom(int id, String title){
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
