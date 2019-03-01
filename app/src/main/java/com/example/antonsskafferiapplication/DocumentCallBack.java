package com.example.antonsskafferiapplication;

import org.w3c.dom.Document;

public interface DocumentCallBack {
    //Lambda function like, used to get response from DatabaseRequest
    void callBackDocument(Document d);
}
