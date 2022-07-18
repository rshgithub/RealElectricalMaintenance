package com.example.electricmaintenance.RecyclerViews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.databinding.MaterialCardBinding;
import com.example.electricmaintenance.databinding.UserInfoCardBinding;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private List<MaterialResponse> materialList;
    private Context context;

    public MaterialAdapter(Context context, List<MaterialResponse> materialList) {
        this.materialList = materialList;
        this.context = context;
    }

    public MaterialAdapter(Activity activity) {
        this.context = activity;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardBinding binding = MaterialCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MaterialAdapter.MaterialViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.MaterialViewHolder holder, int position) {

        MaterialResponse model = materialList.get(position);

       holder.binding.materialCardCostTV.setText(model.getCost()+"");
       holder.binding.materialCardEntryDateTV.setText(model.getEntryDate()+"");
       holder.binding.materialCardEntryUserNameTV.setText(model.getEntryUserName()+"");
       holder.binding.materialCardSerNumTV.setText(model.getSerNum()+"");
       holder.binding.materialCardRecievedQuantityTV.setText(model.getRecievedQuantity()+"");
       holder.binding.materialCardReturnedQuantityTV.setText(model.getReturnedQuantity()+"");
       holder.binding.materialCardUsedQuantityTV.setText(model.getUsedQuantity()+"");
       holder.binding.materialCardUnitNumTV.setText(model.getUnitNum()+"");
       holder.binding.materialCardMaterialNameTV.setText(model.getMaterialName()+"");
       holder.binding.materialCardMaterialStatusTV.setText(model.getMaterialStatus()+"");
       holder.binding.materialCardMaterialNumTV.setText(model.getMaterialNum()+"");


    }

    public void setData(List<MaterialResponse> materialList) {
        this.materialList = materialList;
        notifyDataSetChanged();
    }

    public void addToList(List<MaterialResponse> materialList){
        materialList.addAll(materialList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return materialList != null ? materialList.size() : 0;
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder {

        MaterialCardBinding binding;

        public MaterialViewHolder(MaterialCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
