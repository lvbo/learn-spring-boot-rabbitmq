package com.lvbo.learn.spring.boot.rabbitmq.domain;

import java.io.Serializable;

public class MessageObj implements Serializable {
    private boolean ack;
    private int id;
    private String name;
    private String value;

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
