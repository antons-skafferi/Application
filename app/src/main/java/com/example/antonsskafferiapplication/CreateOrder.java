package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateOrder extends AppCompatActivity {

    FloatingActionButton floatButtonDone;
    ArrayList<LunchOrderCard> orders = new ArrayList<LunchOrderCard>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        LinearLayout lunchLayout = findViewById(R.id.layoutLunchItems);
        LinearLayout drinksLayout = findViewById(R.id.layoutDrinks);;
        LinearLayout aLaCarteLayout = findViewById(R.id.layoutALaCarte);

        floatButtonDone = findViewById(R.id.floatingButtonDone);


        LunchOrderCard lunch1 = new LunchOrderCard(this, "Fisk och pärer");
        orders.add(lunch1);
        LunchOrderCard lunch2 = new LunchOrderCard(this, "Pitepalt");
        orders.add(lunch2);


        LunchOrderCard drink1 = new LunchOrderCard(this, "Rom och cola");
        orders.add(drink1);


        LunchOrderCard aLaCarte1 = new LunchOrderCard(this, "Pytt i panna");
        orders.add(aLaCarte1);

        lunchLayout.addView(lunch1);
        lunchLayout.addView(lunch2);
        drinksLayout.addView(drink1);
        aLaCarteLayout.addView(aLaCarte1);

        floatButtonDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveOnToOverview();
            }
        });
    }


    private void moveOnToOverview(){
        //Creates an array of OrderObj that contains the name of the food and quantity. Use: getFoodName and getQuantity.
        ArrayList<OrderObj> orderDetails = new ArrayList<OrderObj>();
        for(int i=0; i<orders.size(); i++){
            if(orders.get(i).getFoodQuantity() > 0){
                orderDetails.add(new OrderObj(orders.get(i).getFoodName(), orders.get(i).getFoodQuantity()));
            }
        }

        //Gets table number from intent with getExtra()
        Intent intent = getIntent();
        String tableNumber = intent.getStringExtra(ChooseTable.EXTRA_MESSAGE_TABLE_NUMBER);


        Intent i = new Intent(this, Overview.class);
        i.putParcelableArrayListExtra("orderObjectArray", orderDetails);//Get array with i.getParcelableArrayListExtra("orderObjectArray")
        i.putExtra("tableNumber", tableNumber);
        startActivity(i);
    }

}
