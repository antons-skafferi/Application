package com.example.antonsskafferiapplication;

public class OrderDetails {
    private String orderName;
    private String amount;
    private String dateTime;
    private String orderId;

    public OrderDetails(String orderName, String amount, String dateTime, String orderId){
        this.orderName = orderName;
        this.amount = amount;
        this.dateTime = dateTime;
        this.orderId = orderId;
    }

    public String getOrderName(){
        return orderName;
    }

    public String getAmount(){
        return amount;
    }

    public String getDateTime(){
        dateTime = dateTime.replace("+01:00", "Z[UTC]");
        return dateTime;
    }

    public String getOrderId(){
        return orderId;
    }
}
