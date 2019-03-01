package com.example.antonsskafferiapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KitchenOrderCard extends CardView {

    private TextView table;
    private TextView orderItem1;
    private TextView orderItem2;

    //TODO get orders from database
    public KitchenOrderCard(Context context) {
        super(context);

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
        orderItem1 = new TextView(context);
        orderItem2 = new TextView(context);

        table.setText("Bord 1");
        table.setTextSize(30);
        orderItem1.setText("1x Pytt i panna");
        orderItem1.setTextSize(36);
        orderItem2.setText("2x Pannkakor");
        orderItem2.setTextSize(36);

        cardLayout.addView(table);
        cardLayout.addView(orderItem1);
        cardLayout.addView(orderItem2);

        addView(cardLayout);
    }
}
