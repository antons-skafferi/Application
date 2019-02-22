package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent in = getIntent();
        ArrayList<OrderObj> orderObjectArray = in.getParcelableArrayListExtra("orderObjectArray");
        for(int i=0; i<orderObjectArray.size(); i++){
            System.out.println(orderObjectArray.get(i).getFoodName()+": "+Integer.toString(orderObjectArray.get(i).getQuantity()));
        }
    }
}
