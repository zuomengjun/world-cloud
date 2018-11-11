package com.win.world.order.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 7767329212197903995L;

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
