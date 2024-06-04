package com.ki.models;

public abstract class Payment {

    private int customerId;
    private String date;
    private double amount;

    public Payment(int customerId, String date, double amount) {
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}