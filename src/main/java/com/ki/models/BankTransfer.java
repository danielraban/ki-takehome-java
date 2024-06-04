package com.ki.models;

public class BankTransfer extends Payment {

    private int bankAccountId;

    public BankTransfer(int customerId, String date, double amount, int bankAccountId) {
        super(customerId, date, amount);
        this.bankAccountId = bankAccountId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
