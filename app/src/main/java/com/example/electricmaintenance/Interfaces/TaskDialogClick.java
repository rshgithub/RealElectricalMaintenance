package com.example.electricmaintenance.Interfaces;

import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;

import java.util.List;

public interface TaskDialogClick {

    void callBack(List<TaskResponse> list);
}
