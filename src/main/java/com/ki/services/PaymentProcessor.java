package com.ki.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ki.Config;
import com.ki.models.BankTransfer;
import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.ShareOrder;
public class PaymentProcessor {

    private ShareEngine shareEngine;

    public PaymentProcessor() {
        this.shareEngine = new ShareEngine();
    }

    public void processPayments(String paymentFilePath, String paymentType, double sharePrice) throws IOException {
        List<Payment> payments = loadPayments(paymentFilePath, paymentType);

        System.out.println("Loaded " + payments.size() + " payments.");

        BigDecimal sharePriceBD = BigDecimal.valueOf(sharePrice);
        BigDecimal feeRate = Config.getPaymentFeeRate();

        List<ShareOrder> shareOrders = payments.stream()
                .map(payment -> {
                    BigDecimal amountBD = BigDecimal.valueOf(payment.getAmount());
                    BigDecimal adjustedAmount = amountBD.subtract(amountBD.multiply(feeRate)); // Subtract fee
                    int shares = adjustedAmount.divide(sharePriceBD, BigDecimal.ROUND_DOWN).intValue();
                    System.out.println("Creating ShareOrder: customerId=" + payment.getCustomerId() + ", shares=" + shares);
                    return new ShareOrder(payment.getCustomerId(), shares);
                })
                .collect(Collectors.toList());

        shareEngine.processShareOrders(shareOrders);
    }

    List<Payment> loadPayments(String paymentFilePath, String paymentType) throws IOException {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(paymentFilePath))) {
            String line;
            // Skip header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println("Read line: " + line);
                String[] values = line.split(",");
                if ("card".equalsIgnoreCase(paymentType) && values.length == 5) {
                    int customerId = Integer.parseInt(values[0]);
                    String date = values[1];
                    double amount = Double.parseDouble(values[2]);
                    String cardId = values[3];
                    String cardStatus = values[4];
                    if ("processed".equalsIgnoreCase(cardStatus)) {
                        payments.add(new Card(customerId, date, amount, cardId));
                    }
                } else if ("bank".equalsIgnoreCase(paymentType) && values.length == 4) {
                    int customerId = Integer.parseInt(values[0]);
                    String date = values[1];
                    double amount = Double.parseDouble(values[2]);
                    int bankAccountId = Integer.parseInt(values[3]);
                    payments.add(new BankTransfer(customerId, date, amount, bankAccountId));
                }
            }
        }
        return payments;
    }
}