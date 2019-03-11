package com.example.antonsskafferiapplication.jsonretrofit.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderList {
    @SerializedName("order")
    @Expose
    private ArrayList<Order> order = null;

    public ArrayList<Order> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }
}
