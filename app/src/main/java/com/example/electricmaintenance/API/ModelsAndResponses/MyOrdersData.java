package com.example.electricmaintenance.API.ModelsAndResponses;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.electricmaintenance.Database.Interface.OrdersDao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity( tableName = "MyOrdersData")
public class MyOrdersData {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;

    @NonNull
    @ColumnInfo(name = "entryDate")
    @SerializedName("entryDate")
    @Expose
    private String entryDate;

    @NonNull
    @ColumnInfo(name = "serNumber")
    @SerializedName("serNumber")
    @Expose
    private int serNumber;

    @NonNull
    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @NonNull
    @ColumnInfo(name = "userImage")
    @SerializedName("userImage")
    @Expose
    private String userImage;

    @NonNull
    @ColumnInfo(name = "userName")
    @SerializedName("userName")
    @Expose
    private String userName;

    @NonNull
    @ColumnInfo(name = "userPhone")
    @SerializedName("userPhone")
    @Expose
    private String userPhone;

    @NonNull
    @ColumnInfo(name = "userState")
    @SerializedName("userState")
    @Expose
    private String userState;

    @NonNull
    @ColumnInfo(name = "entryUserName")
    @SerializedName("entryUserName")
    @Expose
    private String entryUserName;

    @NonNull
    @ColumnInfo(name = "subscriptionNum")
    @SerializedName("subscriptionNum")
    @Expose
    private String subscriptionNum;

    @NonNull
    @ColumnInfo(name = "transferorName")
    @SerializedName("transferorName")
    @Expose
    private String transferorName;

    @NonNull
    @ColumnInfo(name = "signalType")
    @SerializedName("signalType")
    @Expose
    private String signalType;

    @NonNull
    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    private String status;

    public MyOrdersData() {
    }

    public MyOrdersData(String entryDate, int serNumber, String description, String userImage, String userName, String userPhone, String userState, String entryUserName, String subscriptionNum, String transferorName, String signalType, String status) {
        this.entryDate = entryDate;
        this.serNumber = serNumber;
        this.description = description;
        this.userImage = userImage;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userState = userState;
        this.entryUserName = entryUserName;
        this.subscriptionNum = subscriptionNum;
        this.transferorName = transferorName;
        this.signalType = signalType;
        this.status = status;
    }

    public MyOrdersData(int id, String entryDate, int serNumber, String description, String userImage, String userName, String userPhone, String userState, String entryUserName, String subscriptionNum, String transferorName, String signalType, String status) {
        this.id = id;
        this.entryDate = entryDate;
        this.serNumber = serNumber;
        this.description = description;
        this.userImage = userImage;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userState = userState;
        this.entryUserName = entryUserName;
        this.subscriptionNum = subscriptionNum;
        this.transferorName = transferorName;
        this.signalType = signalType;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getSerNumber() {
        return serNumber;
    }

    public void setSerNumber(int serNumber) {
        this.serNumber = serNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserState() {
        return userState;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getEntryUserName() {
        return entryUserName;
    }

    public void setEntryUserName(String entryUserName) {
        this.entryUserName = entryUserName;
    }

    public String getSubscriptionNum() {
        return subscriptionNum;
    }

    public void setSubscriptionNum(String subscriptionNum) {
        this.subscriptionNum = subscriptionNum;
    }

    public String getTransferorName() {
        return transferorName;
    }

    public void setTransferorName(String transferorName) {
        this.transferorName = transferorName;
    }

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }




}
