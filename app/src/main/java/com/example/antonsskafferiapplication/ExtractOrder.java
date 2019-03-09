package com.example.antonsskafferiapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class ExtractOrder {

    public HashMap<String, OrderData> extractOrders(JSONArray jsonArr, String orderActiveStatus){
        HashMap ordersMade = new HashMap<String, OrderData>();
        try {
            for(int i=0; i<jsonArr.length(); i++){
                JSONObject obj = jsonArr.getJSONObject(i);
                String item = obj.getString("item");
                String orderId = obj.getString("orderId");
                String note = obj.getString("note");
                String tableNumber = obj.getString("tableNumber");
                String active = obj.getString("active");
                String amount = obj.getString("amount");
                String dateTime = obj.getString("dateTime");

                if(active.equals(orderActiveStatus)){
                    //Only insert order item if it's not active

                    //The only way to know which items are in the same order is to look if they have the same table and datetime...
                    String orderKey = tableNumber+dateTime;

                    if(!ordersMade.containsKey(orderKey)){
                        OrderData newOrder = new OrderData(tableNumber);
                        ordersMade.put(orderKey, newOrder);
                    }
                    //Add order item
                    OrderData order = (OrderData)ordersMade.get(orderKey);
                    order.addOrder(item, amount, dateTime, orderId);
                    order.setNote(note);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }





        return ordersMade;
    }



}
