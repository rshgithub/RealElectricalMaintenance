package com.example.electricmaintenance.API.ModelsAndResponses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity( tableName = "TasksTable")
public class TaskResponse {

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
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String taskName;

    @NonNull
    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @NonNull
    @ColumnInfo(name = "executionDate")
    @SerializedName("executionDate")
    @Expose
    private String executionDate;

    @NonNull
    @ColumnInfo(name = "startTime")
    @SerializedName("startTime")
    @Expose
    private String startTime;

    @NonNull
    @ColumnInfo(name = "endTime")
    @SerializedName("endTime")
    @Expose
    private String endTime;

    @NonNull
    @ColumnInfo(name = "duration")
    @SerializedName("duration")
    @Expose
    private String duration;

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

    public TaskResponse() {
    }

    public TaskResponse(int serNum, String taskName, String description, String executionDate, String startTime, String endTime, String duration, String entryUserName, String entryDate) {
        this.serNum = serNum;
        this.taskName = taskName;
        this.description = description;
        this.executionDate = executionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.entryUserName = entryUserName;
        this.entryDate = entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSerNum() {
        return serNum;
    }

    public void setSerNum(int serNum) {
        this.serNum = serNum;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
