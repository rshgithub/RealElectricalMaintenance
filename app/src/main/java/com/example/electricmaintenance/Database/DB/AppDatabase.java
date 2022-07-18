package com.example.electricmaintenance.Database.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.Database.Interface.MaterialDao;
import com.example.electricmaintenance.Database.Interface.OrdersDao;
import com.example.electricmaintenance.Database.Interface.ProcessDao;
import com.example.electricmaintenance.Database.Interface.TasksDao;

@TypeConverters({DataConverter.class})
@Database(entities = {MyOrdersData.class, MaterialResponse.class, TaskResponse.class, ProcessResponse.class}, version = 1 , exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OrdersDao ordersDao();

    public abstract TasksDao tasksDao();

    public abstract ProcessDao processDao();

    public abstract MaterialDao materialDao();
}
