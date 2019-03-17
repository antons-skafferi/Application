package com.example.antonsskafferiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class KitchenActivity extends AppCompatActivity implements DocumentCallBack {
    private LinearLayout orderCardLayout;
    private ExtractOrder orderExtractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        orderCardLayout = findViewById(R.id.orderCardLayout);
        orderExtractor = new ExtractOrder();
        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/api.order1");
    }

    public void createCard(HashMap<String, OrderData> orders){
        Set<String> keys = orders.keySet();

        Iterator it = orders.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            KitchenOrderCard orderCard = new KitchenOrderCard(this, ((OrderData) pair.getValue()).getTableNumber(), (OrderData) pair.getValue(), "Klar!", "2");
            orderCardLayout.setPadding(20,20,20,20);
            orderCardLayout.addView(orderCard);
        }

    }

    @Override
    public void callBackDocument(JSONArray d) {
        //Create cards for each order
        createCard(orderExtractor.extractOrders(d, "1"));
    }

}
