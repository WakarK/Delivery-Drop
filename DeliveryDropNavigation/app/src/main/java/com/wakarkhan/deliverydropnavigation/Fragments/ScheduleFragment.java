package com.wakarkhan.deliverydropnavigation.Fragments;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

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
public class ScheduleFragment extends Fragment implements View.OnClickListener {
    Button btnTimePicker;
    EditText txtTime;
    private int mHour,mMinute;
    private SharedPreferences pref;
    private ProgressBar progressBar;
    private TextView tv_message_schedule;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_schedule,container,false);
        btnTimePicker=(Button)v.findViewById(R.id.btn_set_time);
        txtTime=(EditText)v.findViewById(R.id.et_delivery_time);
        progressBar=(ProgressBar)v.findViewById(R.id.progress_schedule);
        tv_message_schedule=(TextView)v.findViewById(R.id.tv_message_schedule);
        btnTimePicker.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pref=getActivity().getPreferences(0);
    }

    @Override
    public void onClick(View v) {
        if (v==btnTimePicker){
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                txtTime.setText(hourOfDay + ":" + minute);
                                updateScheduledTime(txtTime.getText().toString(),pref.getString(Constants.USER_ID,""));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
        }
    }

    private void updateScheduledTime(String scheduledTime,String user_id){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit .create(RequestInterface.class);
        User user =new User();
        user.setUser_id(user_id);
        user.setScheduled_time(scheduledTime);
        ServerRequest request = new ServerRequest();
        request.setOperation("updateScheduledTime");
        request.setUser(user);
        Call<ServerResponse> responseCall = requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    progressBar.setVisibility(View.GONE);
                    tv_message_schedule.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("DeliveryDrop","failed");
                tv_message_schedule.setVisibility(View.VISIBLE);
                tv_message_schedule.setText(t.getLocalizedMessage());
            }
        });
    }
}