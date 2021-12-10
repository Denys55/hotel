package com.ithotel.entity;

import java.io.Serializable;

public enum Role implements Serializable {
    CLIENT (1,"client"),
    MANAGER (2, "manager"),
    ADMIN (3, "admin");

    private final int id;
    private final String title;

    Role(int id, String title){
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}


