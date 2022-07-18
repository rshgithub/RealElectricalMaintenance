package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AreaResponse {

    @SerializedName("area")
    @Expose
    private List<String> area = null;

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

}
