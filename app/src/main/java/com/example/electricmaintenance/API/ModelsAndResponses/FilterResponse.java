package com.example.electricmaintenance.API.ModelsAndResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterResponse {

    @SerializedName("serNumber")
    @Expose
    private int serNumber;

    @SerializedName("signalNum")
    @Expose
    private int signalNum;

    @SerializedName("subscriptionNum")
    @Expose
    private int subscriptionNum;

    @SerializedName("peopleName")
    @Expose
    private String peopleName;

    @SerializedName("residence")
    @Expose
    private String residence;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("converter")
    @Expose
    private String converter;

    @SerializedName("signalType")
    @Expose
    private String signalType;


    public FilterResponse() {
    }

    public FilterResponse(int serNumber, int signalNum, int subscriptionNum, String peopleName, String residence, String state, String area, String converter, String signalType) {
        this.serNumber = serNumber;
        this.signalNum = signalNum;
        this.subscriptionNum = subscriptionNum;
        this.peopleName = peopleName;
        this.residence = residence;
        this.state = state;
        this.area = area;
        this.converter = converter;
        this.signalType = signalType;
    }

    public int getSerNumber() {
        return serNumber;
    }

    public void setSerNumber(int serNumber) {
        this.serNumber = serNumber;
    }

    public int getSignalNum() {
        return signalNum;
    }

    public void setSignalNum(int signalNum) {
        this.signalNum = signalNum;
    }

    public int getSubscriptionNum() {
        return subscriptionNum;
    }

    public void setSubscriptionNum(int subscriptionNum) {
        this.subscriptionNum = subscriptionNum;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getConverter() {
        return converter;
    }

    public void setConverter(String converter) {
        this.converter = converter;
    }

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }
}
