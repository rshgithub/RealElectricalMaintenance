package com.example.electricmaintenance.RecyclerViews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.databinding.OrderDetailsTasksCardBinding;
import com.example.electricmaintenance.databinding.UserInfoCardBinding;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskResponse> tasksList;
    private Context context;

    public TaskAdapter(Context context, List<TaskResponse> tasksList) {
        this.tasksList = tasksList;
        this.context = context;
    }

    public TaskAdapter(Activity activity) {
        this.context = activity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderDetailsTasksCardBinding binding = OrderDetailsTasksCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {

        TaskResponse model = tasksList.get(position);
 
        holder.binding.tasksSerNumTV.setText(model.getSerNum()+"");
        holder.binding.tasksTaskDescriptionTV.setText(model.getDescription()+"");
        holder.binding.tasksDurationTV.setText(model.getDuration()+"");
        holder.binding.tasksEndTimeTV.setText(model.getEndTime()+"");
        holder.binding.tasksStartTimeTV.setText(model.getStartTime()+"");
        holder.binding.tasksEntryDateTV.setText(model.getEntryDate()+"");
        holder.binding.tasksEntryUserNameTV.setText(model.getEntryUserName()+"");
        holder.binding.tasksExecutionDateTV.setText(model.getExecutionDate()+"");
        holder.binding.tasksTaskNameTV.setText(model.getTaskName()+"");

    }

    public void setData(List<TaskResponse> tasksList) {
        this.tasksList = tasksList;
        notifyDataSetChanged();
    }

    public void addToList(List<TaskResponse> tasksList){
        tasksList.addAll(tasksList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasksList != null ? tasksList.size() : 0;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        OrderDetailsTasksCardBinding binding;

        public TaskViewHolder(OrderDetailsTasksCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
