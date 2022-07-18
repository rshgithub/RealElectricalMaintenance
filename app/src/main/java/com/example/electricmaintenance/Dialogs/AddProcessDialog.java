package com.example.electricmaintenance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Interfaces.ProcessDialogClick;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.AddCarDialogBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProcessDialog extends Dialog  {

    AddCarDialogBinding binding ;
    List<ProcessResponse> processList  ;
    ProcessDialogClick onDialogClick;
    Service service;
    String token;

    public AddProcessDialog(Context context , ProcessDialogClick onDialogClick) {
        super(context);
        this.onDialogClick = onDialogClick;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        service = Creator.getClient().create(Service.class);

        binding = AddCarDialogBinding.inflate(LayoutInflater.from(getContext()));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());

        setCancelable(true);

        binding.processCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.processResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.processET.getText().clear();

            }
        });

        binding.processSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.processET.equals("")) {
                    Toast.makeText(getContext(), "املأ الحقل المطلوب للحفظ", Toast.LENGTH_SHORT).show();
                } else {
                    Constraints.showProgress(getContext());
                    addNewProcess();
                }
            }
        });


    }

    private void addNewProcess() {

        AddNewProcessResponse process = new AddNewProcessResponse(binding.processET.getText().toString());
        service.postNewProcess(process,"Bearer "+token).enqueue(new Callback<List<ProcessResponse>>() {
            @Override
            public void onResponse(Call<List<ProcessResponse>> call, Response<List<ProcessResponse>> response) {
                if (response.body() != null) {
                    processList = response.body();
                    onDialogClick.callBack(processList);
                    dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<ProcessResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}
