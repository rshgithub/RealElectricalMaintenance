package com.example.electricmaintenance.API.ModelsAndResponses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity( tableName = "ProcessTable")
public class ProcessResponse {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;

    @NonNull
    @ColumnInfo(name = "orderId")
    @SerializedName("orderId")
    @Expose
    private int orderId;

    @NonNull
    @ColumnInfo(name = "entryDate")
    @SerializedName("entryDate")
    @Expose
    private String entryDate;

    @NonNull
    @ColumnInfo(name = "entryUserName")
    @SerializedName("entryUserName")
    @Expose
    private String entryUserName;

    @NonNull
    @ColumnInfo(name = "processName")
    @SerializedName("processName")
    @Expose
    private String processName;

    public ProcessResponse() {
    }

    public ProcessResponse(int id, String entryDate, String entryUserName, String processName) {
        this.id = id;
        this.entryDate = entryDate;
        this.entryUserName = entryUserName;
        this.processName = processName;
    }

    public ProcessResponse(@NonNull String entryDate, @NonNull String entryUserName, @NonNull String processName) {
        this.entryDate = entryDate;
        this.entryUserName = entryUserName;
        this.processName = processName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryUserName() {
        return entryUserName;
    }

    public void setEntryUserName(String entryUserName) {
        this.entryUserName = entryUserName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
