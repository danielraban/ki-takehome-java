package com.ki.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void testCardPayment() {
        Card card = new Card(123, "2023-05-20", 100.0, "card123");
        assertEquals(123, card.getCustomerId());
        assertEquals("2023-05-20", card.getDate());
        assertEquals(100.0, card.getAmount(), 0.0);
        assertEquals("card123", card.getCardId());
    }

    @Test
    public void testBankTransfer() {
        BankTransfer bankTransfer = new BankTransfer(456, "2023-05-21", 200.0, 789);
        assertEquals(456, bankTransfer.getCustomerId());
        assertEquals("2023-05-21", bankTransfer.getDate());
        assertEquals(200.0, bankTransfer.getAmount(), 0.0);
        assertEquals(789, bankTransfer.getBankAccountId());
    }
}