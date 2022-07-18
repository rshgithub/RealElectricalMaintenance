package com.example.electricmaintenance.Database.DB;

import androidx.room.TypeConverter;

import com.example.electricmaintenance.API.ModelsAndResponses.Links;
import com.example.electricmaintenance.API.ModelsAndResponses.Meta;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromOrdersDataList(List<MyOrdersData> ordersDataList) {
        if (ordersDataList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MyOrdersData>>() {}.getType();
        String json = gson.toJson(ordersDataList, type);
        return json;
    }

    @TypeConverter
    public List<MyOrdersData> toOrdersData(String ordersDataString) {
        if (ordersDataString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MyOrdersData>>() {}.getType();
        List<MyOrdersData> ordersDataList = gson.fromJson(ordersDataString, type);
        return ordersDataList;
    }

    //---------------------------------------------------------------------------------------------

    @TypeConverter
    public String fromLinksList(Links linksList) {
        if (linksList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Links>() {}.getType();
        String json = gson.toJson(linksList, type);
        return json;
    }

    @TypeConverter
    public Links toLinks(String linksString) {
        if (linksString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Links>() {}.getType();
        Links linksList = gson.fromJson(linksString, type);
        return linksList;
    }

    //---------------------------------------------------------------------------------------------

    @TypeConverter
    public String fromMetaList(Meta metaList) {
        if (metaList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Meta>() {}.getType();
        String json = gson.toJson(metaList, type);
        return json;
    }

    @TypeConverter
    public Meta toMeta(String metaString) {
        if (metaString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Meta>() {}.getType();
        Meta metaList = gson.fromJson(metaString, type);
        return metaList;
    }
}