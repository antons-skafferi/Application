package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateOrder extends AppCompatActivity implements DocumentCallBack{

    FloatingActionButton floatButtonDone;
    ArrayList<LunchOrderCard> orders = new ArrayList<LunchOrderCard>();

    private LinearLayout lunchLayout;
    private LinearLayout drinksLayout;
    private LinearLayout aLaCarteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        lunchLayout = findViewById(R.id.layoutLunchItems);
        drinksLayout = findViewById(R.id.layoutDrinks);
        aLaCarteLayout = findViewById(R.id.layoutALaCarte);

        floatButtonDone = findViewById(R.id.floatingButtonDone);


        floatButtonDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveOnToOverview();
            }
        });

        //since using 127.0.0.1 only uses the internal localhost in the phone we need to use 10.0.2.2 which redirects us to the computer's localhost
        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/api.lunch");
        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/api.drink");
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

    @Override
    public void callBackDocument(Document d) {
        //Parse lunches
        ArrayList<String> lunches = parseItem("lunch", "lunchId", d);
        for(int i=0; i<lunches.size(); i++){
            LunchOrderCard lunchCard = new LunchOrderCard(this, lunches.get(i));
            orders.add(lunchCard);
            lunchLayout.addView(lunchCard);
        }


        //Parse drinks
        ArrayList<String> drinks = parseItem("drink", "drinkId", d);
        for(int i=0; i<drinks.size(); i++){
            LunchOrderCard drinkCard = new LunchOrderCard(this, drinks.get(i));
            orders.add(drinkCard);
            drinksLayout.addView(drinkCard);
        }

    }

    private ArrayList<String> parseItem(String parentElementId, String targetElemName, Document d){
        NodeList elements = d.getElementsByTagName(parentElementId);
        ArrayList<String> results = new ArrayList<String>();
        for(int i=0; i<elements.getLength(); i++){
            Node elem = elements.item(i);
            NodeList lunchData = elem.getChildNodes();
            String elementName = "";
            for(int x=0; x<lunchData.getLength(); x++){
                if(lunchData.item(x).getNodeName().equals(targetElemName)){
                    elementName = lunchData.item(x).getTextContent();
                }
            }
            results.add(elementName);
        }
        return results;
    }


}
