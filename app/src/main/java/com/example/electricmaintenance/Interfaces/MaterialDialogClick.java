package com.example.electricmaintenance.Interfaces;

import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;

import java.util.List;

public interface MaterialDialogClick {

    void callBack(List<MaterialResponse> list);
}
