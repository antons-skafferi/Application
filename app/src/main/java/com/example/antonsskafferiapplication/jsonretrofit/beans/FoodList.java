package com.example.antonsskafferiapplication.jsonretrofit.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodList {
    @SerializedName("food")
    @Expose
    private ArrayList<Food> food = null;

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }
}
