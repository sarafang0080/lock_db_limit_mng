package com.example.ratelimit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAccess {

    @Id
    private String userId;
    private long timestamp;
    private int counter;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}