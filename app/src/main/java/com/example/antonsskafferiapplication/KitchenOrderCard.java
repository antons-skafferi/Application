package com.example.antonsskafferiapplication;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KitchenOrderCard extends LinearLayout {

    private TextView table;
    private String bord = "Bord 1";

    public KitchenOrderCard(Context context) {
        super(context);

        table = new TextView(context);
        table.setTextSize(20);
        table.setText(bord);

        addView(table);
    }
}
