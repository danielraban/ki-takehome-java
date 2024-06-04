package com.ki.models;

public class ShareOrder {

    private int customerId;
    private int shares;

    public ShareOrder(int customerId, int shares) {
        this.customerId = customerId;
        this.shares = shares;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
