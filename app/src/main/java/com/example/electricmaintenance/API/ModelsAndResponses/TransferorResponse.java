package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TransferorResponse {

    @SerializedName("transferor")
    @Expose
    private List<String> transferor = null;

    public List<String> getTransferor() {
        return transferor;
    }

    public void setTransferor(List<String> transferor) {
        this.transferor = transferor;
    }

}
