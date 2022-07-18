package com.example.electricmaintenance.API.ModelsAndResponses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity( tableName = "MaterialsTable")
public class MaterialResponse {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "orderId")
    @SerializedName("orderId")
    @Expose
    private int orderId;

    @NonNull
    @ColumnInfo(name = "serNum")
    @SerializedName("serNum")
    @Expose
    private int serNum;

    @NonNull
    @ColumnInfo(name = "materialNum")
    @SerializedName("materialNum")
    @Expose
    private int materialNum;

    @NonNull
    @ColumnInfo(name = "unitNum")
    @SerializedName("unitNum")
    @Expose
    private int unitNum;

    @NonNull
    @ColumnInfo(name = "recievedQuantity")
    @SerializedName("recievedQuantity")
    @Expose
    private int recievedQuantity;

    @NonNull
    @ColumnInfo(name = "returnedQuantity")
    @SerializedName("returnedQuantity")
    @Expose
    private int returnedQuantity;

    @NonNull
    @ColumnInfo(name = "usedQuantity")
    @SerializedName("usedQuantity")
    @Expose
    private int usedQuantity;

    @NonNull
    @ColumnInfo(name = "cost")
    @SerializedName("cost")
    @Expose
    private String cost;

    @NonNull
    @ColumnInfo(name = "materialName")
    @SerializedName("materialName")
    @Expose
    private String materialName;

    @NonNull
    @ColumnInfo(name = "materialStatus")
    @SerializedName("materialStatus")
    @Expose
    private String materialStatus;

    @NonNull
    @ColumnInfo(name = "entryUserName")
    @SerializedName("entryUserName")
    @Expose
    private String entryUserName;

    @NonNull
    @ColumnInfo(name = "entryDate")
    @SerializedName("entryDate")
    @Expose
    private String entryDate;


    public MaterialResponse() {
    }

    public MaterialResponse(int serNum, int materialNum, int unitNum, int recievedQuantity, int returnedQuantity, int usedQuantity, String cost, String materialName, String materialStatus, String entryUserName, String entryDate) {
        this.serNum = serNum;
        this.materialNum = materialNum;
        this.unitNum = unitNum;
        this.recievedQuantity = recievedQuantity;
        this.returnedQuantity = returnedQuantity;
        this.usedQuantity = usedQuantity;
        this.cost = cost;
        this.materialName = materialName;
        this.materialStatus = materialStatus;
        this.entryUserName = entryUserName;
        this.entryDate = entryDate;
    }

    public int getSerNum() {
        return serNum;
    }

    public void setSerNum(int serNum) {
        this.serNum = serNum;
    }

    public int getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(int materialNum) {
        this.materialNum = materialNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public int getRecievedQuantity() {
        return recievedQuantity;
    }

    public void setRecievedQuantity(int recievedQuantity) {
        this.recievedQuantity = recievedQuantity;
    }

    public int getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(int returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public int getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(int usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getEntryUserName() {
        return entryUserName;
    }

    public void setEntryUserName(String entryUserName) {
        this.entryUserName = entryUserName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
