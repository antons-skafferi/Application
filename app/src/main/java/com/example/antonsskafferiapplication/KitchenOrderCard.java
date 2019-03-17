package com.example.antonsskafferiapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class KitchenOrderCard extends CardView {

    private TextView table;
    private Button orderIsComplete;
    private OrderData order;
    private String onClickDoneNewOrderActiveVal;

    //TODO get orders from database
    public KitchenOrderCard(Context context, String titleTable, OrderData orderObj, String completeButton, String onClickNewActiveValue) {
        /*
        * onClickNewActiveValue
        * 0 = completed and delivered
        * 1 = done but not delivered
        * 2 = done and ready for delivery
        * */
        super(context);
        onClickDoneNewOrderActiveVal = onClickNewActiveValue;
        order = orderObj;
        LinearLayout layoutCard = new LinearLayout(context);
        layoutCard.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.parseColor("#e8e8e8"));
        setContentPadding(20, 20, 20, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(20,20,20,20);
        setLayoutParams(params);
        LinearLayout cardLayout = new LinearLayout(context);
        cardLayout.setOrientation(LinearLayout.VERTICAL);


        table = new TextView(context);
        table.setText("Bord: "+titleTable);
        table.setTextSize(30);
        cardLayout.addView(table);

        //loop through order's items and add a textview for each
        for(int i=0; i<orderObj.getItems().size(); i++){
            TextView orderItem1 = new TextView(context);
            orderItem1.setText(orderObj.getItems().get(i).getOrderName() + " x"+orderObj.getItems().get(i).getAmount());
            orderItem1.setTextSize(36);
            cardLayout.addView(orderItem1);
        }

        TextView notesView = new TextView(context);
        notesView.setText(orderObj.getNotes());
        cardLayout.addView(notesView);
        layoutCard.addView(cardLayout);

        orderIsComplete = new Button(context);
        orderIsComplete.setText(completeButton);
        orderIsComplete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new updateRemoveRow(order.getItems()).execute();
                ((ViewManager)getParent()).removeView(KitchenOrderCard.this);
            }
        });
        layoutCard.addView(orderIsComplete);
        addView(layoutCard);
    }





    class updateRemoveRow extends AsyncTask<Void, Void ,Void> {
        private ArrayList<OrderDetails> itemList;

        public updateRemoveRow(ArrayList<OrderDetails> listOfItems){
            itemList = listOfItems;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(OrderDetails induvidualItemOrderData : itemList){
                try {
                    URL url = new URL("http://10.0.2.2:8080/website/webresources/api.order1/"+induvidualItemOrderData.getOrderId());
                    HttpURLConnection client = (HttpURLConnection) url.openConnection();
                    client.setRequestMethod("PUT");
                    client.setRequestProperty("Content-Type", "application/json");
                    client.setDoOutput(true);
                    client.setDoInput(true);

                    OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream());

                    //Creates JSON object to insert with put to update order item's activity status
                    String JSONObject = "{\"active\":"+onClickDoneNewOrderActiveVal+",\"dateTime\":\""+induvidualItemOrderData.getDateTime()+"\",\"prepared\":1";
                    JSONObject += ",\"tableNumber\":"+order.getTableNumber();
                    JSONObject += ",\"item\":\""+induvidualItemOrderData.getOrderName()+"\"";
                    JSONObject += ",\"amount\":"+induvidualItemOrderData.getAmount();
                    JSONObject += ",\"note\":\""+order.getNotes()+"\"";
                    JSONObject += ",\"orderId\":"+induvidualItemOrderData.getOrderId();
                    JSONObject += "}";
                    out.write(JSONObject);

                    //System.out.println(JSONObject);

                    out.close();

                    //Don't really know why but client.getInputStream() needs to be called for the upload to happen.
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String response = "";
                    String line;
                    while((line = br.readLine()) != null){
                        response += line+"\n";
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }
    }
}
