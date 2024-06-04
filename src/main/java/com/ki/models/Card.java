package com.ki.models;

public class Card extends Payment {

    private String cardId;

    public Card(int customerId, String date, double amount, String cardId) {
        super(customerId, date, amount);
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
