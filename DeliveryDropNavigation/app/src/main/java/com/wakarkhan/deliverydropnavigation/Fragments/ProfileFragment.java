package com.wakarkhan.deliverydropnavigation.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wakarkhan.deliverydropnavigation.Models.Constants;
import com.wakarkhan.deliverydropnavigation.Models.ServerResponse;
import com.wakarkhan.deliverydropnavigation.Models.User;
import com.wakarkhan.deliverydropnavigation.R;
import com.wakarkhan.deliverydropnavigation.RequestInterface;
import com.wakarkhan.deliverydropnavigation.ServerRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 17-10-2016.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    private TextView tv_username;
    private TextView tv_address,tv_message;
    private ImageView iv_edit_address_icon,iv_edit_username_icon,iv_profile_picture,iv_edit_profile_picture;
    private EditText et_new_addr1,et_new_city;
    private ProgressBar progressBar;
    private AlertDialog dialog;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pref=getActivity().getPreferences(0);
        tv_username.setText(pref.getString(Constants.NAME,""));
        tv_address.setText(pref.getString(Constants.NAME,"")+" \n "+pref.getString(Constants.ADDR1,"")+"\n"+pref.getString(Constants.USER_ID,"")+"\n"+pref.getString(Constants.CITY,""));
    }

    private void initViews(View view){
        tv_username=(TextView)view.findViewById(R.id.tv_username);
        tv_address =(TextView)view.findViewById(R.id.tv_address);
        iv_edit_username_icon=(ImageView)view.findViewById(R.id.iv_edit_username_icon);
        iv_edit_username_icon=(ImageView)view.findViewById(R.id.iv_edit_address_icon);
        iv_profile_picture=(ImageView)view.findViewById(R.id.iv_profile_picture);
        iv_edit_profile_picture=(ImageView)view.findViewById(R.id.iv_edit_profile_picture);
        iv_edit_profile_picture.setOnClickListener(this);
        iv_edit_username_icon.setOnClickListener(this);
        iv_edit_address_icon.setOnClickListener(this);
    }

    private void showEditAddressDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_address,null);
        et_new_addr1=(EditText)view.findViewById(R.id.et_new_addr1);
        et_new_city=(EditText)view.findViewById(R.id.et_new_city);
        tv_message=(TextView)view.findViewById(R.id.tv_message);
        progressBar=(ProgressBar)view.findViewById(R.id.progress_edit);
        builder.setView(view);
        builder.setTitle("Edit Address");
        builder.setPositiveButton("Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog =builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_addr1 = et_new_addr1.getText().toString();
                String new_city = et_new_city.getText().toString();
                if (!new_addr1.isEmpty() && !new_city.isEmpty()){
                    progressBar.setVisibility(View.VISIBLE);
                    updateAddressProcess(pref.getString(Constants.USER_ID,""),new_addr1,new_city);
                }else {
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Fields are empty");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_edit_profile_picture:

                break;
            case R.id.iv_edit_address_icon:
                showEditAddressDialog();
                break;
            case R.id.iv_edit_username_icon:

                break;
        }
    }

    private void updateAddressProcess(String user_id,String new_addr1,String new_city){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user =new User();
        user.setUser_id(user_id);
        user.setNew_addr1(new_addr1);
        user.setNew_city(new_city);
        ServerRequest request =new ServerRequest();
        request.setOperation("updateAddress");
        request.setUser(user);
        Call<ServerResponse> responseCall = requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    progressBar.setVisibility(View.GONE);
                    tv_message.setVisibility(View.GONE);
                    dialog.dismiss();
                    Snackbar.make(getView(),resp.getMessage(),Snackbar.LENGTH_LONG).show();
                }else {
                    progressBar.setVisibility(View.GONE);
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText(resp.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("DeliveryDrop","failed");
                progressBar.setVisibility(View.GONE);
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText(t.getLocalizedMessage());
            }
        });
    }
}