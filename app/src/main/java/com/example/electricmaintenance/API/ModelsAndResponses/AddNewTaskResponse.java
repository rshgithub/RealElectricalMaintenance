package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewTaskResponse {

    @SerializedName("name")
    @Expose
    private String taskName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("executionDate")
    @Expose
    private String executionDate;

    @SerializedName("startTime")
    @Expose
    private String startTime;

    @SerializedName("endTime")
    @Expose
    private String endTime;

    public AddNewTaskResponse() {
    }

    public AddNewTaskResponse(String taskName, String description, String executionDate, String startTime, String endTime) {
        this.taskName = taskName;
        this.description = description;
        this.executionDate = executionDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
