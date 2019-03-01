package com.example.antonsskafferiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class KitchenActivity extends AppCompatActivity {

    private LinearLayout orderCardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        orderCardLayout = findViewById(R.id.orderCardLayout);

        //TODO get orders from database
        for(int i = 0; i < 5; i++){
            KitchenOrderCard orderCard = new KitchenOrderCard(this);
            orderCardLayout.setPadding(20,20,20,20);
            orderCardLayout.addView(orderCard);
        }


    }
}
