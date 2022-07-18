package com.example.electricmaintenance.Database.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;

import java.util.List;

@Dao // data access object
public interface OrdersDao {
    @Query("select * from MyOrdersData")
    public List<MyOrdersData> readAll();

    @Insert
    public long insert(MyOrdersData orders);

    @Insert
    abstract void insertToOrdersList(List<MyOrdersData> ordersList);

    @Update
    public int update(MyOrdersData orders);

    @Delete
    public int delete(MyOrdersData orders);
}
