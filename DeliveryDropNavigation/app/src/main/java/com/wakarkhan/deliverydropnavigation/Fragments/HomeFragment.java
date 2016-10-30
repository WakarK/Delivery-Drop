package com.wakarkhan.deliverydropnavigation.Fragments;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wakarkhan.deliverydropnavigation.R;
import com.wakarkhan.deliverydropnavigation.Models.Constants;

/**
 * Created by HP on 17-10-2016.
 */
public class HomeFragment extends Fragment {
    private TextView tv_address;
    private SharedPreferences pref;

    public static HomeFragment newInstance(String address) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args=new Bundle();
        args.putString("address",address);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_home,container,false);
        tv_address=(TextView)rootview.findViewById(R.id.CustomerAddress);
        Bundle args=getArguments();
        String address=args.getString("address");
        tv_address.setText(address);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pref=getActivity().getPreferences(0);
        tv_address.setText(pref.getString(Constants.NAME,"")+" \n "+pref.getString(Constants.ADDR1,"")+"\n"+pref.getString(Constants.USER_ID,"")+"\n"+pref.getString(Constants.CITY,""));

    }
}