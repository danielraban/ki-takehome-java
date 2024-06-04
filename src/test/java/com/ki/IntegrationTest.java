package com.ki;

import com.ki.LocalRunner;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegrationTest {

    @Test
    public void testSimulateCardPayments() {
        String fixturePath = Fixture.getPath("card_payments_mixed.csv");

        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"123\",\"735\"\n" +
            "\"456\",\"3430\"\n"
        );

        String result = simulatePlatform(fixturePath, "card", new BigDecimal("1.2"));

        assertEquals(expected.trim(), result.trim());
    }

    @Test
    public void testSimulateBankPayments() {
        String fixturePath = Fixture.getPath("bank_payments_example.csv");

        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"789\",\"735\"\n" +
            "\"345\",\"735\"\n"
        );

        String result = simulatePlatform(fixturePath, "bank", new BigDecimal("1.2"));

        assertEquals(expected.trim(), result.trim());
    }

    private String simulatePlatform(String fixturePath, String paymentType, BigDecimal sharePrice) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outputStream));

        String[] args = {fixturePath, paymentType, sharePrice.toString()};
        LocalRunner.main(args);

        System.out.flush();
        System.setOut(oldOut);

        String output = outputStream.toString();

        // Filter the actual output lines
        List<String> outputLines = Arrays.stream(output.split("\n"))
                                         .filter(line -> line.startsWith("\"customer_id\"") || line.matches("\"\\d+\",\"\\d+\""))
                                         .collect(Collectors.toList());

        return String.join("\n", outputLines).trim(); // Ensure no trailing newlines or whitespace
    }
}