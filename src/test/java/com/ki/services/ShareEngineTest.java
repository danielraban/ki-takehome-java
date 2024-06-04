package com.ki.services;

import org.junit.Before;
import org.junit.Test;

import com.ki.models.ShareOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShareEngineTest {

    private ShareEngine shareEngine;

    @Before
    public void setUp() {
        shareEngine = new ShareEngine();
    }

    @Test
    public void testProcessShareOrders() {
        List<ShareOrder> shareOrders = new ArrayList<>();
        shareOrders.add(new ShareOrder(789, 735));
        shareOrders.add(new ShareOrder(345, 735));

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outputStream));

        shareEngine.processShareOrders(shareOrders);

        System.out.flush();
        System.setOut(oldOut);

        String output = outputStream.toString().trim();
        
        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"789\",\"735\"\n" +
            "\"345\",\"735\"\n"
        ).trim();

        assertEquals(expected, output);
    }

    @Test
    public void testProcessEmptyShareOrders() {
        List<ShareOrder> shareOrders = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outputStream));

        shareEngine.processShareOrders(shareOrders);

        System.out.flush();
        System.setOut(oldOut);

        String output = outputStream.toString().trim();

        String expected = "\"customer_id\",\"shares\"";

        assertEquals(expected, output);
    }

    @Test
    public void testProcessSingleShareOrder() {
        List<ShareOrder> shareOrders = new ArrayList<>();
        shareOrders.add(new ShareOrder(123, 500));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outputStream));

        shareEngine.processShareOrders(shareOrders);

        System.out.flush();
        System.setOut(oldOut);

        String output = outputStream.toString().trim();
        
        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"123\",\"500\"\n"
        ).trim();

        assertEquals(expected, output);
    }
}