package com.mohammadnasrallah.maliaassignment.domain;

public class Invoice {
    private int id;
    private int customerId;
    private double amount;
    private String date;

    public Invoice(int id, int customerId, double amount, String date) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public Invoice(int customerId, double amount, String date) {
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

