package com.wakarkhan.deliverydropnavigation.Fragments;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import com.wakarkhan.deliverydropnavigation.R;

/**
 * Created by HP on 17-10-2016.
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener {
    Button btnTimePicker;
    EditText txtTime;
    private int mHour,mMinute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_schedule,container,false);
        btnTimePicker=(Button)v.findViewById(R.id.btn_set_time);
        txtTime=(EditText)v.findViewById(R.id.et_delivery_time);
        btnTimePicker.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==btnTimePicker){
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
        }
    }
}