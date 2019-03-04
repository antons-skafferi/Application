package com.example.antonsskafferiapplication;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class ExtractOrder {

    public HashMap<String, OrderData> extractOrders(Document d, String orderActiveStatus){
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


        return ordersMade;
    }



}
