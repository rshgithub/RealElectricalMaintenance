package com.example.electricmaintenance.API.ModelsAndResponses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Entity
public class OrderResponse {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("data")
    @Expose
    private List<MyOrdersData> data ;

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("meta")
    @Expose
    private Meta meta;

    public OrderResponse() {
    }

    public List<MyOrdersData> getData() {
        return data;
    }

    public void setData(List<MyOrdersData> data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
