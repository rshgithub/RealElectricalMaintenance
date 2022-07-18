package com.example.electricmaintenance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewMaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Interfaces.MaterialDialogClick;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.AddMaterialsDialogBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMaterialDialog extends Dialog {


    AddMaterialsDialogBinding binding ;
    String processName , materialName , unit , usedQuantity , recievedQuantity , returnedQuantity ;
    int materialNum ;
    List<MaterialResponse> materialList  ;
    MaterialDialogClick onDialogClick;
    Service service;
    String token;

    public AddMaterialDialog(Context context , MaterialDialogClick onDialogClick) {
        super(context);
        this.onDialogClick = onDialogClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        service = Creator.getClient().create(Service.class);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = AddMaterialsDialogBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());

        setCancelable(true);

        binding.addMaterialCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.addMaterialClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFeilds();
            }
        });

        binding.addMaterialSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditTextEntries();

                if (binding.addMaterialProcessET.equals("")) {
                    Toast.makeText(getContext(), "املأ حقل اضافة الآلية المطلوب للحفظ", Toast.LENGTH_SHORT).show();
                } else {
                    Constraints.showProgress(getContext());
                    AddNewMaterialResponse newMaterial = new AddNewMaterialResponse(processName,materialName,materialNum,unit,recievedQuantity,returnedQuantity,usedQuantity);
                    postNewMaterial(newMaterial);
                }
            }
        });


    }

    private void postNewMaterial(AddNewMaterialResponse newMaterial) {

        service.postNewMaterial(newMaterial,"Bearer "+token).enqueue(new Callback<List<MaterialResponse>>() {
            @Override
            public void onResponse(Call<List<MaterialResponse>> call, Response<List<MaterialResponse>> response) {
                if (response.body() != null) {
                    materialList = response.body();
                    onDialogClick.callBack(materialList);
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MaterialResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void clearAllFeilds(){
        binding.addMaterialProcessET.getText().clear();
        binding.addMaterialMaterialNameET.getText().clear();
        binding.addMaterialMaterialNumET.getText().clear();
        binding.addMaterialUnitET.getText().clear();
        binding.addMaterialUsedQuantityET.getText().clear();
        binding.addMaterialRecievedQuantityET.getText().clear();
        binding.addMaterialReturnedQuantityET.getText().clear();
    }

    private void getEditTextEntries(){
        processName = binding.addMaterialProcessET.getText().toString();
        materialName = binding.addMaterialMaterialNameET.getText().toString();
        materialNum = Integer.parseInt(binding.addMaterialMaterialNumET.getText().toString());
        unit = binding.addMaterialUnitET.getText().toString();
        usedQuantity = binding.addMaterialUsedQuantityET.getText().toString();
        recievedQuantity = binding.addMaterialRecievedQuantityET.getText().toString();
        returnedQuantity = binding.addMaterialReturnedQuantityET.getText().toString();

    }



}
