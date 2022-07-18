package com.example.electricmaintenance.RecyclerViews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.MaintenanceActivities.OrderDetailsActivity;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.UserInfoCardBinding;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<MyOrdersData> ordersList;
    private Context context;

    public OrderAdapter(Context context, List<MyOrdersData> ordersList) {
        this.ordersList = ordersList;
        this.context = context;
    }


    public OrderAdapter(Activity activity) {
        this.context = activity;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserInfoCardBinding binding = UserInfoCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderAdapter.OrderViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        MyOrdersData model = ordersList.get(position);

        holder.binding.ordersCardUserName.setText(model.getUserName()+"");
        holder.binding.ordersCardUserPhoneNum.setText(model.getUserPhone()+"");
        holder.binding.ordersCardUserStateTV.setText(model.getUserState()+"");
        holder.binding.ordersCardSerNum.setText(model.getSerNumber()+"");
        holder.binding.ordersCardSubscriptionNoTV.setText(model.getSubscriptionNum()+"");
        holder.binding.ordersCardDescription.setText(model.getDescription()+"");
        holder.binding.ordersCardSignalTypeTV.setText(model.getSignalType()+"");
        holder.binding.ordersCardEntryNameTV.setText(model.getEntryUserName()+"");
        holder.binding.ordersCardTransfarerTV.setText(model.getTransferorName()+"");
        holder.binding.ordersCardEntryDate.setText(model.getEntryDate()+"");

        if(model.getUserImage() != null && !model.getUserImage().isEmpty()){
            Glide.with(context).load(model.getUserImage()).circleCrop().into(holder.binding.ordersCardUserImage);
        } else {
            holder.binding.ordersCardUserImage.setImageResource(R.drawable.ic_user);
        }

        setUpActions(holder, position);
    }

    private void setUpActions(OrderViewHolder holder, int position) {
        holder.binding.ordersCardOptionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyOrdersData orderItem = ordersList.get(position);
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra(Constraints.ORDER_ID, orderItem.getId());
                context.startActivity(intent);
            }
        });
    }

    public void setData(List<MyOrdersData> ordersList) {
        this.ordersList = ordersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ordersList != null ? ordersList.size() : 0;
    }


    public void addToList(List<MyOrdersData> ordersList){
        ordersList.addAll(ordersList);
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        UserInfoCardBinding binding;

        public OrderViewHolder(UserInfoCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
