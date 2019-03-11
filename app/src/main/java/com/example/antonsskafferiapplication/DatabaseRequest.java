package com.example.antonsskafferiapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DatabaseRequest extends AsyncTask<String, Void, JSONArray> {

    private DocumentCallBack callBack;

    public DatabaseRequest(CreateOrder callBackClass){
        callBack = (DocumentCallBack)callBackClass;
    }

    public DatabaseRequest(KitchenActivity callBackClass){
        callBack = (DocumentCallBack)callBackClass;
    }

    public DatabaseRequest(ShowFinishedOrders callBackClass){
        callBack = (DocumentCallBack)callBackClass;
    }

    public DatabaseRequest(MyPagerAdapter callBackClass){
        callBack = (DocumentCallBack)callBackClass;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {
        for(String s : strings){
            try {
                URL url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                String jsonString = "";
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = br.readLine()) != null){
                    jsonString += line;
                }
                //Document xmlResponse = builder.parse(conn.getInputStream());
                return new JSONArray(jsonString);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



    @Override
    protected void onPostExecute(JSONArray jsonArr){
        if(jsonArr != null){
            callBack.callBackDocument(jsonArr);
        }
    }


}
