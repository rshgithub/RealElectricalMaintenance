package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StateResponse {

    @SerializedName("state")
    @Expose
    private List<String> state = null;

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

}
