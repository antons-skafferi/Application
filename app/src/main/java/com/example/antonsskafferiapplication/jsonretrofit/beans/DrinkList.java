package com.example.antonsskafferiapplication.jsonretrofit.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DrinkList {
    @SerializedName("Drink")
    @Expose
    private ArrayList<Drink> drink = null;

    public ArrayList<Drink> getDrink() {
        return drink;
    }

    public void setFood(ArrayList<Drink> drink) {
        this.drink = drink;
    }
}
