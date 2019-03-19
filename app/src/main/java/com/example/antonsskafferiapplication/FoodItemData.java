package com.example.antonsskafferiapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItemData implements Parcelable {
    private String foodName;
    private String foodDescription;


    public FoodItemData(String foodName, String foodDescription){
        this.foodName = foodName;
        this.foodDescription = foodDescription;
    }

    public FoodItemData(Parcel in) {
        super();
        this.foodName = in.readString();
        this.foodDescription = in.readString();
    }

    public String getFoodName(){
        return foodName;
    }

    public String getFoodDescription(){
        return foodDescription;
    }


    public static final Creator<FoodItemData> CREATOR = new Creator<FoodItemData>() {
        @Override
        public FoodItemData createFromParcel(Parcel in) {
            return new FoodItemData(in);
        }

        @Override
        public FoodItemData[] newArray(int size) {
            return new FoodItemData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
