package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ChooseTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);
    }

    //Called when the user clicks a tablenumber
    public void sendTableNumber(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
