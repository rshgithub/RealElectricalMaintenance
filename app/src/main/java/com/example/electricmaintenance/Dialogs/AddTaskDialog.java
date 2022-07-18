package com.example.electricmaintenance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewTaskResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Interfaces.TaskDialogClick;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.AddTaskDialogBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskDialog  extends Dialog {

    AddTaskDialogBinding binding ;
    String taskName , description , executionDate , startTime , endTime ;
    String token;
    List<TaskResponse> taskList  ;
    TaskDialogClick onDialogClick;
    Service service;

    public AddTaskDialog(Context context , TaskDialogClick onDialogClick) {
        super(context);
        this.onDialogClick = onDialogClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        service = Creator.getClient().create(Service.class);

        binding = AddTaskDialogBinding.inflate(LayoutInflater.from(getContext()));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());

        setCancelable(true);

        binding.addTaskCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.addTaskResetBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                clearAllFeilds();
            }
        });


        binding.addTaskSaveBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getEditTextEntries();
            if (checkDialogFeilds()) {
                Toast.makeText(getContext(), "املأ الحقول المطلوبة للحفظ", Toast.LENGTH_SHORT).show();
            } else {
                Constraints.showProgress(getContext());
                AddNewTaskResponse newTask = new AddNewTaskResponse(taskName,description,executionDate,startTime,endTime);
                postNewTask(newTask);
            }
        }
        });


    }

    private void postNewTask(AddNewTaskResponse newTask) {

        service.postNewTask(newTask,"Bearer "+token).enqueue(new Callback<List<TaskResponse>>() {
            @Override
            public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                if (response.body() != null) {
                    taskList = response.body();
                    onDialogClick.callBack(taskList);
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<TaskResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private boolean checkDialogFeilds(){
        if (taskName.equals("") && description.equals("") && executionDate.equals("") && startTime.equals("") &&  endTime.equals("") ) {
            return true;
        } else {
            return false;
        }
    }

    private void clearAllFeilds(){
        binding.addTaskTaskNameET.getText().clear();
        binding.addTaskTaskDescriptionET.getText().clear();
        binding.addTaskExecutionDateET.getText().clear();
        binding.addTaskStartTimeET.getText().clear();
        binding.addTaskEndTimeET.getText().clear();
    }


    private void getEditTextEntries(){
        taskName = binding.addTaskTaskNameET.getText().toString();
        description = binding.addTaskTaskDescriptionET.getText().toString();
        executionDate = binding.addTaskExecutionDateET.getText().toString();
        startTime = binding.addTaskEndTimeET.getText().toString();
        endTime = binding.addTaskStartTimeET.getText().toString();
    }


}
