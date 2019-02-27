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
import java.util.ArrayList;
import java.util.List;

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
        layoutFoodQuantity = findViewById(R.id.antalOverviewLayout);

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
                new PostData().execute("http://10.0.2.2:8080/website/webresources/api.order1");
            }
        });


    }





    public String extractQueryString(){
        String returnString;
        String arrayOfOrdersObj = "[";

        for(Pair<String, String> p : orderDetails){
            String JSONObject = "{\"name\":\""+p.first+"\",\"quantity\":"+"\""+p.second+"\"},";
            arrayOfOrdersObj += JSONObject;
        }
        //Remove last trailing comma
        arrayOfOrdersObj = arrayOfOrdersObj.substring(0, arrayOfOrdersObj.length()-1);
        //Add closing array bracket
        arrayOfOrdersObj += "]";

        returnString = "tableId="+tableId+"&orderArray="+arrayOfOrdersObj;

        System.out.println(returnString);

        return returnString;
    }


    class PostData extends AsyncTask<String, Void, Void> {



        @Override
        protected Void doInBackground(String... strings) {

            for(String s : strings){
                try {
                    URL url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    if(conn.getResponseCode() != 200){
                        System.out.println("Server error: "+Integer.toString(conn.getResponseCode()));
                        break;
                    }

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    bw.write(extractQueryString());


                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String response = "";
                    String line;
                    while((line = br.readLine()) != null){
                        response += line;
                    }
                    System.out.println(response);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){

        }
    }
}
