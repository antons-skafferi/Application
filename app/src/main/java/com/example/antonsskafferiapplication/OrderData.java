package com.example.antonsskafferiapplication;

import android.util.Pair;

import java.util.ArrayList;

public class OrderData {
    /*Is used in kitchenActivity to load complete orders from the database*/
    private String tableNumber;
    private String note;
    private ArrayList<OrderDetails> orders;


    public OrderData(String t){
        orders = new ArrayList<>();
        tableNumber = t;
    }

    public void addOrder(String orderName, String amount, String dateTime, String orderId){
        OrderDetails orderDet = new OrderDetails(orderName, amount, dateTime, orderId);

        orders.add(orderDet);
    }

    public void setNote(String n){
        note = n;
    }

    public String getNotes(){
        return note;
    }

    public String getTableNumber(){
        return tableNumber;
    }



    public ArrayList<OrderDetails> getItems(){
        return orders;
    }



}
