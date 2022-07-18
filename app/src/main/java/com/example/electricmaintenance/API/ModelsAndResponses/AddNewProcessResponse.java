package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewProcessResponse {

    @SerializedName("processName")
    @Expose
    private String processName;


    public AddNewProcessResponse() {
    }

    public AddNewProcessResponse(String processName) {
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
