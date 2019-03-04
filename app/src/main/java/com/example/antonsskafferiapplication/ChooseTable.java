package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ChooseTable extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_TABLE_NUMBER = "tableNumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);

        Button seeCompletedOrders = findViewById(R.id.seeCompletedOrders);
        seeCompletedOrders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTable.this, ShowFinishedOrders.class);
                startActivity(intent);
            }
        });
    }

    //Called when the user clicks a table number
    //Starts CreateOrder activity with table number as putExtra()
    public void sendTableNumber(View view){
        Intent intent = new Intent(this, CreateOrder.class);
        Button button = (Button) view;
        String message =  button.getText().toString();
        intent.putExtra(EXTRA_MESSAGE_TABLE_NUMBER, message);
        startActivity(intent);
    }
}
