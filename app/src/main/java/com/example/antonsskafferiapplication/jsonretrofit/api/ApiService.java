package com.example.antonsskafferiapplication.jsonretrofit.api;


import com.example.antonsskafferiapplication.jsonretrofit.beans.OrderList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiService {
    /*
Retrofit get annotation with our URL
And our method that will return us the List of EmployeeList
*/
    @Headers("content-type: application/json")
    @GET("api.order1")
    Call<OrderList> getOrders();

    @Headers("content-type: application/json")
    @GET("api.food")
    Call<OrderList> getFoods();

    @Headers("content-type: application/json")
    @GET("api.drink")
    Call<OrderList> getDrinks();
}
