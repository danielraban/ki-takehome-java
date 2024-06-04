package com.ki.services;

import org.junit.Before;
import org.junit.Test;

import com.ki.models.BankTransfer;
import com.ki.models.Card;
import com.ki.models.Payment;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    private PaymentProcessor paymentProcessor;

    @Before
    public void setUp() {
        paymentProcessor = new PaymentProcessor();
    }

    @Test
    public void testLoadCardPayments() throws IOException {
        List<Payment> payments = paymentProcessor.loadPayments("src/test/fixtures/card_payments_mixed.csv", "card");
        assertEquals(2, payments.size());

        Payment payment1 = payments.get(0);
        assertTrue(payment1 instanceof Card);
        assertEquals(123, payment1.getCustomerId());
        assertEquals("2019-01-12", payment1.getDate());
        assertEquals(900.0, payment1.getAmount(), 0.0);
        assertEquals("30", ((Card) payment1).getCardId());

        Payment payment2 = payments.get(1);
        assertTrue(payment2 instanceof Card);
        assertEquals(456, payment2.getCustomerId());
        assertEquals("2019-01-20", payment2.getDate());
        assertEquals(4200.0, payment2.getAmount(), 0.0);
        assertEquals("10", ((Card) payment2).getCardId());
    }

    @Test
    public void testLoadBankPayments() throws IOException {
        List<Payment> payments = paymentProcessor.loadPayments("src/test/fixtures/bank_payments_example.csv", "bank");
        assertEquals(2, payments.size());

        Payment payment1 = payments.get(0);
        assertTrue(payment1 instanceof BankTransfer);
        assertEquals(789, payment1.getCustomerId());
        assertEquals("2018-10-25", payment1.getDate());
        assertEquals(900.0, payment1.getAmount(), 0.0);
        assertEquals(20, ((BankTransfer) payment1).getBankAccountId());

        Payment payment2 = payments.get(1);
        assertTrue(payment2 instanceof BankTransfer);
        assertEquals(345, payment2.getCustomerId());
        assertEquals("2018-11-03", payment2.getDate());
        assertEquals(900.0, payment2.getAmount(), 0.0);
        assertEquals(60, ((BankTransfer) payment2).getBankAccountId());
    }
}
