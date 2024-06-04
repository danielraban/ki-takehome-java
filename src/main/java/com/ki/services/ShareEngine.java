package com.ki.services;

import java.util.List;

import com.ki.models.ShareOrder;

public class ShareEngine {

    public void processShareOrders(List<ShareOrder> shareOrders) {
        System.out.println("\"customer_id\",\"shares\"");
        for (ShareOrder order : shareOrders) {
            System.out.println("\"" + order.getCustomerId() + "\",\"" + order.getShares() + "\"");
        }
    }
}