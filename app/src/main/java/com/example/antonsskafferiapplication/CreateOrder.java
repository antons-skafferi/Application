package com.example.antonsskafferiapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateOrder extends AppCompatActivity {

    FloatingActionButton floatButtonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        LinearLayout lunchLayout = findViewById(R.id.layoutLunchItems);
        LinearLayout drinksLayout = findViewById(R.id.layoutDrinks);;
        LinearLayout aLaCarteLayout = findViewById(R.id.layoutALaCarte);

        floatButtonDone = findViewById(R.id.floatingButtonDone);

        LunchOrderCard lunch1 = new LunchOrderCard(this, "Fisk och pärer");
        LunchOrderCard lunch2 = new LunchOrderCard(this, "Pitepalt");


        LunchOrderCard drink1 = new LunchOrderCard(this, "Rom och cola");


        LunchOrderCard aLaCarte1 = new LunchOrderCard(this, "Pytt i panna");

        lunchLayout.addView(lunch1);
        lunchLayout.addView(lunch2);
        drinksLayout.addView(drink1);
        aLaCarteLayout.addView(aLaCarte1);
    }
}
