package com.wakarkhan.deliverydropnavigation;


import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.wakarkhan.deliverydropnavigation.Models.Orders;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by HP on 27-10-2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Orders> order;

    public DataAdapter(ArrayList<Orders> order){
        this.order=order;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_orders,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewholder, int i) {
        viewholder.tv_product_name.setText(order.get(i).getProductName());
        viewholder.tv_website.setText(order.get(i).getWebsiteName());
        viewholder.tv_order_status.setText(order.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return order.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_product_name,tv_website,tv_order_status;
        private ImageView iv_order;
        public ViewHolder(View view){
            super(view);

            tv_product_name=(TextView)view.findViewById(R.id.tv_product_name);
            tv_website=(TextView)view.findViewById(R.id.tv_website);
            tv_order_status=(TextView)view.findViewById(R.id.tv_order_status);
            iv_order=(ImageView)view.findViewById(R.id.iv_order);
        }
    }
}
