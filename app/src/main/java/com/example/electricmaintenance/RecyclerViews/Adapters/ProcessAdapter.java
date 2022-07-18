package com.example.electricmaintenance.RecyclerViews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.databinding.OrderDetailsTasksCardBinding;
import com.example.electricmaintenance.databinding.ProcessCardBinding;

import java.util.ArrayList;
import java.util.List;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.processViewHolder> {

    private List<ProcessResponse> processList;
    private Context context;

    public ProcessAdapter(Context context, List<ProcessResponse> processList) {
        this.processList = processList;
        this.context = context;
    }

    public ProcessAdapter(Activity activity) {
        this.context = activity;
    }

    @NonNull
    @Override
    public processViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProcessCardBinding binding = ProcessCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new processViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.processViewHolder holder, int position) {

        ProcessResponse model = processList.get(position);

        holder.binding.processCardEntryUserNameTV.setText(model.getEntryUserName()+"");
        holder.binding.processCardEntryDateTV.setText(model.getEntryDate()+"");
        holder.binding.processCardId.setText(model.getId()+"");
        holder.binding.processCardEntryUserNameTV.setText(model.getProcessName()+"");

    }

    public void setData(List<ProcessResponse> processList) {
        this.processList = processList;
        notifyDataSetChanged();
    }

    public void addToList(List<ProcessResponse> processList){
        processList.addAll(processList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return processList != null ? processList.size() : 0;
    }

    public static class processViewHolder extends RecyclerView.ViewHolder {

        ProcessCardBinding binding;

        public processViewHolder(ProcessCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

}
