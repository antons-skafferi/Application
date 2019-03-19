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

    private ArrayList<FoodItemData> mainMenuItems;
    private ArrayList<FoodItemData> appetizerItems;
    private ArrayList<FoodItemData> dessertItems;
    private ArrayList<FoodItemData> childMenuItems;
    private ArrayList<FoodItemData> drinksMenuItems;

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
                mainMenuCreator = MainMenuCreator.newInstance(mainMenuItems);
                return mainMenuCreator;
            case 1:
                appetizerFragment = AppetizerFragment.newInstance(appetizerItems);
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
                    mainMenuItems.add(new FoodItemData(foodObj.getString("dish"), foodObj.getString("description")));
                }else if(foodObj.getString("category").equals("appetizer_menu")){
                    appetizerItems.add(new FoodItemData(foodObj.getString("dish"), foodObj.getString("description")));
                }else if(foodObj.getString("category").equals("dessert_menu")){
                    dessertItems.add(new FoodItemData(foodObj.getString("dish"), foodObj.getString("description")));
                }else if(foodObj.getString("category").equals("child_menu")){
                    childMenuItems.add(new FoodItemData(foodObj.getString("dish"), foodObj.getString("description")));
                }
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        //Parse drinks
        for(int i=0; i<jsonRootArr.length(); i++){
            try {
                JSONObject foodObj = jsonRootArr.getJSONObject(i);
                drinksMenuItems.add(new FoodItemData(foodObj.getString("drinkName"), ""));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }


    }




}
