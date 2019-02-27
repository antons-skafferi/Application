package com.example.antonsskafferiapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderObj implements Parcelable {
    private String foodName;
    private int quantity;

    public OrderObj(String name, int q){
        foodName = name;
        quantity = q;
    }

    protected OrderObj(Parcel in) {
        foodName = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<OrderObj> CREATOR = new Creator<OrderObj>() {
        @Override
        public OrderObj createFromParcel(Parcel in) {
            return new OrderObj(in);
        }

        @Override
        public OrderObj[] newArray(int size) {
            return new OrderObj[size];
        }
    };

    public String getFoodName(){
        return foodName;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeInt(quantity);
    }
}
