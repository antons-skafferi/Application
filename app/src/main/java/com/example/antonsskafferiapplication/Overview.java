package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;


public class Overview extends AppCompatActivity {

    private String tableId;
    private LinearLayout layoutFoodName;
    private LinearLayout layoutFoodQuantity;

    private ArrayList<Pair<String, String>> orderDetails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);



        layoutFoodName = findViewById(R.id.foodListContainer);
        layoutFoodQuantity = findViewById(R.id.numberOverviewLayout);

        Intent in = getIntent();
        ArrayList<OrderObj> orderObjectArray = in.getParcelableArrayListExtra("orderObjectArray");
        for(int i=0; i<orderObjectArray.size(); i++){
            System.out.println(orderObjectArray.get(i).getFoodName()+": "+Integer.toString(orderObjectArray.get(i).getQuantity()));
            TextView foodName = new TextView(this);
            foodName.setText(orderObjectArray.get(i).getFoodName());
            layoutFoodName.addView(foodName);

            TextView foodQuantity = new TextView(this);
            foodQuantity.setText(Integer.toString(orderObjectArray.get(i).getQuantity()));
            layoutFoodQuantity.addView(foodQuantity);

            orderDetails.add(new Pair<String, String>(orderObjectArray.get(i).getFoodName(), Integer.toString(orderObjectArray.get(i).getQuantity())));
        }

        tableId = in.getStringExtra("tableNumber");


        Button sendPost = findViewById(R.id.sendOrder);
        sendPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new PostData().execute(extractQueryString());
            }
        });


    }



    public String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z[UTC]'",
                Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public ArrayList<String> extractQueryString(){
        /*Example string
        *
        * {"active":1,"amount":5,"dateTime":"2019-02-26T10:28:50Z[UTC]","item":"Drink_2","note":"-kaloua2","prepared":1,"tableNumber":5}
        *
        * */

        String currentTimeString = getDateTime();

        String returnString;
        ArrayList<String> queries = new ArrayList<>();

        for(Pair<String, String> p : orderDetails){
            String JSONObject = "{\"active\":1,\"dateTime\":\""+currentTimeString+"\",\"note\":\"-NOTE HERE\",\"prepared\":1";
            JSONObject += ",\"tableNumber\":"+tableId;
            JSONObject += ",\"item\":\""+p.first+"\"";
            JSONObject += ",\"amount\":"+p.second;
            JSONObject += "}";
            queries.add(JSONObject);
        }



        return queries;
    }


    class PostData extends AsyncTask<ArrayList<String>, Void, Void> {



        private void sendJSON(String JSONData){
            try {
                URL url = new URL("http://10.0.2.2:33819/website/webresources/api.order1");
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");

                client.setRequestProperty("Content-Type", "application/json");
                client.setDoInput(true);
                client.setDoOutput(true);

                OutputStream os = client.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(os, "UTF-8")));
                writer.write(JSONData);

                writer.flush();
                writer.close();
                os.close();

                System.out.println("Response code: "+Integer.toString(client.getResponseCode()));



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(ArrayList<String>... listOfJSON) {


            for(ArrayList<String> JSONList : listOfJSON){
                for(String json : JSONList){
                    sendJSON(json);
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){

        }
    }
}
