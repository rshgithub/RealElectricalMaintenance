package com.example.electricmaintenance.Interfaces;

import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;

import java.util.List;

public interface FilterDialogClick {

    void callBack(List<MyOrdersData> list);
}
