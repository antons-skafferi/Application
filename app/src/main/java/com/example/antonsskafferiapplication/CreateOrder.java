package com.example.antonsskafferiapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class CreateOrder extends FragmentActivity implements DocumentCallBack{

    private FloatingActionButton floatButtonDone;
    private ArrayList<FoodsOrderCard> orders = new ArrayList<>();

    private TabLayout myTabLayout;

    private ArrayList<JSONArray> databaseData;

    private ViewPager pager;

    private MyPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        pager = (ViewPager) findViewById(R.id.viewPagerOrder);
        pager.setOffscreenPageLimit(5);
        databaseData = new ArrayList<>();
        //Find views for elements
        floatButtonDone = findViewById(R.id.floatingButtonDone);
        myTabLayout = findViewById(R.id.tabBar);


        myTabLayout.addTab(myTabLayout.newTab().setText("Förrätt"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Huvudrätt"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Efterrätt"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Barnmeny"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Drickor"));
        myTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        floatButtonDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveOnToOverview();
            }
        });



        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/entities.food");
        new DatabaseRequest(this).execute("http://10.0.2.2:8080/website/webresources/api.drink");


        pager.setCurrentItem(myTabLayout.getSelectedTabPosition());

        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void moveOnToOverview(){
        //Creates an array of OrderObj that contains the name of the food and quantity. Use: getFoodName and getQuantity.
        orders = pagerAdapter.getAllOrders();
        ArrayList<OrderObj> orderDetails = new ArrayList<OrderObj>();
        for(int i=0; i<orders.size(); i++){
            if(orders.get(i).getFoodQuantity() > 0){
                orderDetails.add(new OrderObj(orders.get(i).getFoodName(), orders.get(i).getFoodQuantity(), orders.get(i).getIsLunch()));
            }
        }
        
        //Gets table number from intent with getExtra()
        Intent intent = getIntent();
        String tableNumber = intent.getStringExtra(ChooseTable.EXTRA_MESSAGE_TABLE_NUMBER);


        //Intent starts a new activity
        Intent i = new Intent(this, Overview.class);
        i.putParcelableArrayListExtra("orderObjectArray", orderDetails);//Get array with i.getParcelableArrayListExtra("orderObjectArray")
        i.putExtra("tableNumber", tableNumber);
        startActivity(i);
    }

    @Override
    public void callBackDocument(JSONArray jsonArr) {
        databaseData.add(jsonArr);

        if(databaseData.size() == 2){
            ((ViewManager)findViewById(R.id.progressBarCreateOrder).getParent()).removeView(findViewById(R.id.progressBarCreateOrder));


            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), this, databaseData);
            pager.setAdapter(pagerAdapter);

            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(myTabLayout));
        }

    }


}
