package com.example.antonsskafferiapplication;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public class DatabaseRequest extends AsyncTask<String, Void, Document> {

    private DocumentCallBack callBack;

    public DatabaseRequest(CreateOrder callBackClass){
        callBack = (DocumentCallBack)callBackClass;
    }

    @Override
    protected Document doInBackground(String... strings) {
        for(String s : strings){
            try {
                URL url = new URL(s);
                 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                 conn.setRequestMethod("GET");
                 conn.setRequestProperty("Accept", "application/xml");

                 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                 DocumentBuilder builder = factory.newDocumentBuilder();

                 Document xmlResponse = builder.parse(conn.getInputStream());
                 return xmlResponse;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



    @Override
    protected void onPostExecute(Document xmlDoc){
        if(xmlDoc != null){
            //xmlDoc.getDocumentElement().normalize();
            //clean(xmlDoc);
            callBack.callBackDocument(xmlDoc);
        }
    }


}
