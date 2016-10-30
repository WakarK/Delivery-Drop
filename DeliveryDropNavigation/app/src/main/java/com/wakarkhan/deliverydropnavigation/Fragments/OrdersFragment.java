package com.wakarkhan.deliverydropnavigation.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;

import com.wakarkhan.deliverydropnavigation.DataAdapter;
import com.wakarkhan.deliverydropnavigation.Models.Orders;
import com.wakarkhan.deliverydropnavigation.OrdersServerResponse;
import com.wakarkhan.deliverydropnavigation.R;
import com.wakarkhan.deliverydropnavigation.RequestInterface;


import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 17-10-2016.
 */
public class OrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Orders> data;
    private DataAdapter adapter;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_orders,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View v){
        pref=getActivity().getPreferences(0);
        recyclerView=(RecyclerView)v.findViewById(R.id.order_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<OrdersServerResponse> call=request.getJSON();
        call.enqueue(new Callback<OrdersServerResponse>() {
            @Override
            public void onResponse(Call<OrdersServerResponse> call, Response<OrdersServerResponse> response) {
                OrdersServerResponse ordersServerResponse = response.body();
                data = new ArrayList<>(Arrays.asList(ordersServerResponse.getOrder()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OrdersServerResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
