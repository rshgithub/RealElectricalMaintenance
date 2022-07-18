package com.example.electricmaintenance.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewTaskResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.OrderResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Interfaces.CloseSingalDialogClick;
import com.example.electricmaintenance.Interfaces.TaskDialogClick;
import com.example.electricmaintenance.MaintenanceActivities.OrderDetailsActivity;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.AddCarDialogBinding;
import com.example.electricmaintenance.databinding.CloseSignalWarningDialogBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloseSignalDialog extends Dialog {

    CloseSignalWarningDialogBinding binding;
    CloseSingalDialogClick onDialogClick;
    Service service;
    String token;
    private int orderId ;

    public CloseSignalDialog(Context context, int orderId , CloseSingalDialogClick onDialogClick) {
        super(context);
        this.orderId = orderId;
        this.onDialogClick = onDialogClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        service = Creator.getClient().create(Service.class);

        binding = CloseSignalWarningDialogBinding.inflate(LayoutInflater.from(getContext()));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());

        setCancelable(true);

        binding.closeWarningCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.closeWarningAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOrderStatus(orderId);
            }
        });

        binding.closeWarningCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    private void changeOrderStatus(int id) {

        service.orderStatus(orderId, "Bearer "+token, "closed").enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                Constraints.showProgress(getContext());
                onDialogClick.callBack();
                dismiss();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }
}


