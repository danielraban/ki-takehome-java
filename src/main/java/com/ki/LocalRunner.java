package com.ki;
import java.io.IOException;

import com.ki.services.PaymentProcessor;

public class LocalRunner {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java -jar target/ki-takehome-java-1.0-SNAPSHOT.jar <payment_file_path> <payment_type> <share_price>");
            return;
        }

        String paymentFilePath = args[0];
        String paymentType = args[1];
        double sharePrice = Double.parseDouble(args[2]);

        PaymentProcessor paymentProcessor = new PaymentProcessor();

        try {
            System.out.println("Processing payments...");
            paymentProcessor.processPayments(paymentFilePath, paymentType, sharePrice);
            System.out.println("Processing completed.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to process payments: " + e.getMessage());
        }
    }
}