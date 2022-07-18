
package com.example.electricmaintenance.API.ModelsAndResponses;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SignalTypeResponse {

    @SerializedName("signalType")
    @Expose
    private List<String> signalType = null;

    public List<String> getSignalType() {
        return signalType;
    }

    public void setSignalType(List<String> signalType) {
        this.signalType = signalType;
    }

}
