package com.example.antonsskafferiapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class DrinksMenuFragment extends Fragment {
    private ArrayList<FoodsOrderCard> orders = new ArrayList<FoodsOrderCard>();

    public DrinksMenuFragment() {
        // Required empty public constructor
    }


    public static DrinksMenuFragment newInstance(ArrayList<FoodItemData> foodData) {
        DrinksMenuFragment fragment = new DrinksMenuFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("parcelObjs", foodData);
        fragment.setArguments(args);
        return fragment;
    }



    public ArrayList<FoodsOrderCard> getOrders(){
        return orders;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_layout, container, false);


        LinearLayout mainLayout = v.findViewById(R.id.mainLayout);
        //LinearLayout fragLayout = new LinearLayout(v.getContext());
        //layoutMainMenu
        Bundle args = getArguments();
        ArrayList<FoodItemData> foodNames = args.getParcelableArrayList("parcelObjs");
        for(int i=0; i<foodNames.size(); i++){
            FoodsOrderCard foodCard = new FoodsOrderCard(getActivity(), foodNames.get(i).getFoodName(), foodNames.get(i).getFoodDescription(), false);
            orders.add(foodCard);
            mainLayout.addView(foodCard);
        }

        // Inflate the layout for this fragment
        return v;
    }



}
