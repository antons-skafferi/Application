package com.example.antonsskafferiapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.ProgressBar;

class FoodsOrderCard extends LinearLayout {


    private int quantity = 0;
    private TextView foodName;
    private Button increase;
    private TextView quantityText;
    private Button decrease;
    private boolean isLunch = false;
    private SeekBar orderSlider;

    public FoodsOrderCard(Context context, String foodNameString, boolean isLunch) {
        super(context);
        this.isLunch = isLunch;
        setBackgroundColor(Color.parseColor("#e8e8e8"));

        LinearLayout buttonContainer = new LinearLayout(context);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                15
        );
        buttonContainer.setLayoutParams(buttonParams);

        LinearLayout.LayoutParams sliderParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                1
        );

        setPadding(8,8,8,16);
        foodName = new TextView(context);
        foodName.setTextSize(20);
        foodName.setText(foodNameString);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                1
        );
        //foodName.setLayoutParams(textParams);
        addView(foodName);



        /*increase = new Button(context);
        increase.setText("+");
        buttonContainer.addView(increase);*/

        quantityText = new TextView(context);
        quantityText.setText("0");
        buttonContainer.addView(quantityText);

        /*decrease = new Button(context);
        decrease.setText("-");
        buttonContainer.addView(decrease);*/

        orderSlider = new SeekBar(context);
        orderSlider.setLayoutParams(sliderParams);
        buttonContainer.addView(orderSlider);

        addView(buttonContainer);

        orderSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar orderSlider, int progress, boolean fromUser) {

                int minValue = 0;
                int maxValue = 10;
                orderSlider.setMax(maxValue);

                if (progress < minValue) progress = minValue;

                quantity = progress;
                quantityText.setText(Integer.toString(quantity));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityText.setText(Integer.toString(quantity));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity--;
                if(quantity < 0) quantity = 0;
                quantityText.setText(Integer.toString(quantity));
            }
        });*/

    }


    public String getFoodName(){
        return foodName.getText().toString();
    }

    public int getFoodQuantity(){
        return quantity;
    }

    public boolean getIsLunch(){
        return isLunch;
    }

}
