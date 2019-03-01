package com.example.antonsskafferiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.login_button);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseTable.class);
                startActivity(intent);
            }
        };

        button.setOnClickListener(click);



        Button buttonTestKitchenApp = findViewById(R.id.buttonTestKitchenApp);
        buttonTestKitchenApp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KitchenActivity.class);
                startActivity(intent);
            }
        });
    }
}
