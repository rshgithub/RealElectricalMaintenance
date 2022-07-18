package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewMaterialResponse {

    @SerializedName("processName")
    @Expose
    private String processName;

    @SerializedName("materialName")
    @Expose
    private String materialName;

    @SerializedName("materialNum")
    @Expose
    private int materialNum;

    @SerializedName("unit")
    @Expose
    private String unit;

    @SerializedName("recievedQuantity")
    @Expose
    private String recievedQuantity;

    @SerializedName("returnedQuantity")
    @Expose
    private String returnedQuantity;

    @SerializedName("usedQuantity")
    @Expose
    private String usedQuantity;

    public AddNewMaterialResponse() {
    }

    public AddNewMaterialResponse(String processName, String materialName, int materialNum, String unit, String recievedQuantity, String returnedQuantity, String usedQuantity) {
        this.processName = processName;
        this.materialName = materialName;
        this.materialNum = materialNum;
        this.unit = unit;
        this.recievedQuantity = recievedQuantity;
        this.returnedQuantity = returnedQuantity;
        this.usedQuantity = usedQuantity;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(int materialNum) {
        this.materialNum = materialNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRecievedQuantity() {
        return recievedQuantity;
    }

    public void setRecievedQuantity(String recievedQuantity) {
        this.recievedQuantity = recievedQuantity;
    }

    public String getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(String returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public String getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(String usedQuantity) {
        this.usedQuantity = usedQuantity;
    }
}
