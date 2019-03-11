package com.example.antonsskafferiapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<String> mainMenuItems;
    private ArrayList<String> appetizerItems;
    private ArrayList<String> dessertItems;
    private ArrayList<String> childMenuItems;
    private ArrayList<String> drinksMenuItems;

    private MainMenuCreator mainMenuCreator;
    private AppetizerFragment appetizerFragment;
    private DessertMenuFragment dessertMenuFragment;
    private ChildMenuFragment childMenuFragment;
    private DrinksMenuFragment drinksMenuFragment;

    public MyPagerAdapter(FragmentManager fm, Context con, ArrayList<JSONArray> jsonDatabaseData) {
        super(fm);
        mainMenuItems = new ArrayList<>();
        appetizerItems = new ArrayList<>();
        dessertItems = new ArrayList<>();
        childMenuItems = new ArrayList<>();
        drinksMenuItems = new ArrayList<>();
        for(JSONArray data : jsonDatabaseData){
            parseFoodData(data);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                mainMenuCreator = MainMenuCreator.newInstance(appetizerItems);
                return mainMenuCreator;
            case 1:
                appetizerFragment = AppetizerFragment.newInstance(mainMenuItems);
                return appetizerFragment;
            case 2:
                dessertMenuFragment = DessertMenuFragment.newInstance(dessertItems);
                return dessertMenuFragment;
            case 3:
                childMenuFragment = ChildMenuFragment.newInstance(childMenuItems);
                return childMenuFragment;
            case 4:
                drinksMenuFragment = DrinksMenuFragment.newInstance(drinksMenuItems);
                return drinksMenuFragment;
        }
        return null;
    }


    public ArrayList<FoodsOrderCard> getAllOrders(){
        ArrayList<FoodsOrderCard> allOrders = new ArrayList<FoodsOrderCard>();
        for(FoodsOrderCard c : mainMenuCreator.getOrders()){
            allOrders.add(c);
        }
        for(FoodsOrderCard c : appetizerFragment.getOrders()){
            allOrders.add(c);
        }
        for(FoodsOrderCard c : dessertMenuFragment.getOrders()){
            allOrders.add(c);
        }
        for(FoodsOrderCard c : childMenuFragment.getOrders()){
            allOrders.add(c);
        }
        for(FoodsOrderCard c : drinksMenuFragment.getOrders()){
            allOrders.add(c);
        }
        return allOrders;
    }

    public void parseFoodData(JSONArray jsonRootArr) {

        //Parse foods
        for(int i=0; i<jsonRootArr.length(); i++){
            try {
                JSONObject foodObj = jsonRootArr.getJSONObject(i);
                if(foodObj.getString("category").equals("main_menu")){
                    mainMenuItems.add(foodObj.getString("dish"));
                }else if(foodObj.getString("category").equals("appetizer_menu")){
                    appetizerItems.add(foodObj.getString("dish"));
                }else if(foodObj.getString("category").equals("dessert_menu")){
                    dessertItems.add(foodObj.getString("dish"));
                }else if(foodObj.getString("category").equals("child_menu")){
                    childMenuItems.add(foodObj.getString("dish"));
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        //Parse drinks
        for(int i=0; i<jsonRootArr.length(); i++){
            try {
                JSONObject foodObj = jsonRootArr.getJSONObject(i);
                drinksMenuItems.add(foodObj.getString("drinkName"));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }


    }




}
