package com.example.antonsskafferiapplication.jsonretrofit.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("prepared")
    @Expose
    private Integer prepared;
    @SerializedName("tableNumber")
    @Expose
    private Integer tableNumber;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPrepared() {
        return prepared;
    }

    public void setPrepared(Integer prepared) {
        this.prepared = prepared;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

}
