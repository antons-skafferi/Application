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

        KitchenOrderCard orderCard = new KitchenOrderCard(this);
        orderCardLayout.addView(orderCard);
        orderCardLayout.addView(orderCard);
        orderCardLayout.addView(orderCard);


    }
}
