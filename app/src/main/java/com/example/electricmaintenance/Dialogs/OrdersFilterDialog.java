package com.example.electricmaintenance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.Area;
import com.example.electricmaintenance.API.ModelsAndResponses.AreaResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.Example;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.API.ModelsAndResponses.FilterResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.LocationResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.SignalTypeResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.State;
import com.example.electricmaintenance.API.ModelsAndResponses.StateResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.Transferor;
import com.example.electricmaintenance.API.ModelsAndResponses.TransferorResponse;
import com.example.electricmaintenance.API.Service;

import com.example.electricmaintenance.Interfaces.FilterDialogClick;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.OrdersFilterDialogBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFilterDialog extends Dialog {

    String people, areaStr, residenceStr, stateStr, converterStr, signalTypeStr;
    int serNum, signalNum, subscriptionNum;

    Spinner  location, area , state, converter, signalType;
    List<String> locationList , signalTypeList ;

    Example stateResponse;
    int statePosition , areaPosition , transferorPosition ;

    LocationResponse locationResponse;
    SignalTypeResponse signalTypeResponse ;
    List<MyOrdersData> ordersList;

    OrdersFilterDialogBinding binding ;
    FilterDialogClick onDialogClick;
    Service service;
    String token;

    public OrdersFilterDialog(Context context , FilterDialogClick onDialogClick) {
        super(context);
        this.onDialogClick = onDialogClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        service = Creator.getClient().create(Service.class);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);

        binding = OrdersFilterDialogBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());

        binding.ordersFilterAreaSpinner.setEnabled(false);
        binding.ordersFilterAreaSpinner.setClickable(false);
        binding.ordersFilterTransferorSpinner.setEnabled(false);
        binding.ordersFilterTransferorSpinner.setClickable(false);

//        getLocationSpinner();
//        getSignalTypeSpinner();
//        getStateSpinner();

        binding.ordersFilterCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.ordersFilterResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllFeilds();
            }
        });

        binding.ordersFilterStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statePosition = i ;
                if(stateResponse.getState().get(i).getArea().isEmpty()){
                    areaPosition = -1 ;
                    transferorPosition = -1;
                }else{
                    areaPosition = 0 ;
                    if(stateResponse.getState().get(i).getArea().get(0).getTransferor().isEmpty()){
                        transferorPosition = -1 ;
                    }else{
                        transferorPosition = 0 ;
                    }
                    setStateSpinnerAdapter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        binding.ordersFilterAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaPosition = i ;
                if(stateResponse.getState().get(statePosition).getArea().get(i).getTransferor().isEmpty()){
                    transferorPosition = -1;
                }else{
                    transferorPosition = 0 ;
                    setTransferorSpinnerAdapter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        binding.ordersFilterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditTextEntries();

                if (serNum == 0 && signalNum == 0 && subscriptionNum == 0 && people.equals("") && area.equals("") && location.equals("") && state.equals("")
                        && converter.equals("") && signalType.equals("")) {
                    Toast.makeText(getContext(), "املأ املأ على الأقل حقل واحد من الحقول ليتم البحث", Toast.LENGTH_SHORT).show();
                } else {

                    Constraints.showProgress(getContext());
                    FilterResponse filterData = new FilterResponse(serNum, signalNum, subscriptionNum, people, areaStr, residenceStr, stateStr, converterStr, signalTypeStr);
//                    filterOrders(filterData);

                }

            }
        });
    }

    private void filterOrders(FilterResponse filterResponse) {

        service.postFilter(filterResponse,"Bearer "+token).enqueue(new Callback<List<MyOrdersData>>() {
            @Override
            public void onResponse(Call<List<MyOrdersData>> call, Response<List<MyOrdersData>> response) {
                if (response.body() != null) {
                    ordersList = response.body();
                    onDialogClick.callBack(ordersList);
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MyOrdersData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void getLocationSpinner() {

        service.getLocation("Bearer "+token).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.body() != null) {
                    locationResponse = response.body();
                    locationList = locationResponse.getLocation();
                    setLocationSpinnerAdapter(locationList);
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getStateSpinner() {
        service.getState("Bearer "+token).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() != null) {
                    stateResponse = response.body();
                    setStateSpinnerAdapter();
                    binding.ordersFilterAreaSpinner.setEnabled(true);
                    binding.ordersFilterAreaSpinner.setClickable(true);
                    binding.ordersFilterTransferorSpinner.setEnabled(true);
                    binding.ordersFilterTransferorSpinner.setClickable(true);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getSignalTypeSpinner() {
        service.getSignalType("Bearer "+token).enqueue(new Callback<SignalTypeResponse>() {
            @Override
            public void onResponse(Call<SignalTypeResponse> call, Response<SignalTypeResponse> response) {
                if (response.body() != null) {
                    signalTypeResponse = response.body();
                    signalTypeList = signalTypeResponse.getSignalType();
                    setSignalTypeSpinnerAdapter(signalTypeList);
                }
            }

            @Override
            public void onFailure(Call<SignalTypeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void clearAllFeilds(){
        //we can't user for loop to clear all feilds one bc it's not in one single layout , it's nested so we clear every single one alone
        binding.ordersFilterSERET.setText("");
        binding.ordersFilterSignalNumET.setText("");
        binding.ordersFilterSubscriptionET.setText("");
        binding.ordersFilterPeopleET.setText("");
        //reset spinners
        binding.ordersFilterAreaSpinner.setSelection(0);
        binding.ordersFilterStateSpinner.setSelection(0);
        binding.ordersFilterLocationSpinner.setSelection(0);
        binding.ordersFilterTransferorSpinner.setSelection(0);
        binding.ordersFilterSignalTypeSpinner.setSelection(0);
    }


    private void getEditTextEntries(){
        serNum = Integer.parseInt(binding.ordersFilterSERET.getText().toString());
        signalNum = Integer.parseInt(binding.ordersFilterSignalNumET.getText().toString());
        subscriptionNum = Integer.parseInt(binding.ordersFilterSubscriptionET.getText().toString());
        people = binding.ordersFilterPeopleET.getText().toString();

        area = binding.ordersFilterAreaSpinner;
        areaStr = area.getSelectedItem().toString();
        location = binding.ordersFilterLocationSpinner;
        residenceStr = area.getSelectedItem().toString();
        state = binding.ordersFilterStateSpinner;
        stateStr = area.getSelectedItem().toString();
        converter = binding.ordersFilterTransferorSpinner;
        converterStr = area.getSelectedItem().toString();
        signalType = binding.ordersFilterSignalTypeSpinner;
        signalTypeStr = area.getSelectedItem().toString();
    }


    private void setLocationSpinnerAdapter(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        binding.ordersFilterLocationSpinner.setAdapter(adapter);
    }

    private void setSignalTypeSpinnerAdapter(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        binding.ordersFilterSignalTypeSpinner.setAdapter(adapter);
    }

    private void setStateSpinnerAdapter(){
        statePosition = 0 ;
        ArrayList<String> stateList = new ArrayList<>();
        for (int i = 0 ; i < stateResponse.getState().size() ; i++ ){
            stateList.add(stateResponse.getState().get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ordersFilterStateSpinner.setAdapter(adapter);
        setAreaSpinnerAdapter();
    }

    private void setAreaSpinnerAdapter(){
        areaPosition = 0 ;
        ArrayList<String> areaList = new ArrayList<>();
        for (int i = 0 ; i < stateResponse.getState().get(statePosition).getArea().size() ; i++ ){
            areaList.add(stateResponse.getState().get(statePosition).getArea().get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, areaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ordersFilterAreaSpinner.setAdapter(adapter);
        setTransferorSpinnerAdapter();
    }

    private void setTransferorSpinnerAdapter(){
        transferorPosition = 0 ;
        ArrayList<String> tranferorList = new ArrayList<>();
        for (int i = 0 ; i < stateResponse.getState().get(statePosition).getArea().get(areaPosition).getTransferor().size() ; i++ ){
            tranferorList.add(stateResponse.getState().get(statePosition).getArea().get(areaPosition).getTransferor().get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tranferorList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ordersFilterTransferorSpinner.setAdapter(adapter);
    }


}