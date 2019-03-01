package com.example.antonsskafferiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class KitchenActivity extends AppCompatActivity implements DocumentCallBack {

    private LinearLayout orderCardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        orderCardLayout = findViewById(R.id.orderCardLayout);
        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/api.order1");
    }

    public void createCard(HashMap<String, OrderData> orders){
        Set<String> keys = orders.keySet();

        Iterator it = orders.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            KitchenOrderCard orderCard = new KitchenOrderCard(this, (String)pair.getKey(), (OrderData) pair.getValue());
            orderCardLayout.setPadding(20,20,20,20);
            orderCardLayout.addView(orderCard);
        }


    }

    @Override
    public void callBackDocument(Document d) {
        /*Is called when new DatabaseRequest(this).execute is done*/
        NodeList orders = d.getElementsByTagName("order1");
        HashMap ordersMade = new HashMap<String, OrderData>();

        //Loo through each orders items
        for(int i=0; i<orders.getLength(); i++){
            NodeList childNodes = orders.item(i).getChildNodes();

            String item = "";
            String orderId = "";
            String note = "";
            String tableNumber = "";
            String active = "0";
            String amount = "";
            String dateTime = "";


            for(int x=0; x<childNodes.getLength(); x++){
                if(childNodes.item(x).getNodeName().equals("item")){
                    item = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("orderId")){
                    orderId = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("note")){
                    note = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("tableNumber")){
                    tableNumber = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("active")){
                    active = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("amount")){
                    amount = childNodes.item(x).getTextContent();
                }
                if(childNodes.item(x).getNodeName().equals("dateTime")){
                    dateTime = childNodes.item(x).getTextContent();
                }
            }

            if(active.equals("1")){
                //Only insert order item if it's not active


                if(!ordersMade.containsKey(tableNumber)){
                    OrderData newOrder = new OrderData(tableNumber);
                    ordersMade.put(tableNumber, newOrder);
                }
                //Add order item
                OrderData order = (OrderData)ordersMade.get(tableNumber);
                order.addOrder(item, amount, dateTime, orderId);
                order.setNote(note);
            }
        }

        //Create cards for each order
        createCard(ordersMade);

    }
}
