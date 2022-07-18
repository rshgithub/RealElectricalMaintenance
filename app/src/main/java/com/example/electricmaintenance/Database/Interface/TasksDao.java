package com.example.electricmaintenance.Database.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import java.util.List;

@Dao
public interface TasksDao {
    @Query("select * from TasksTable")
    public List<TaskResponse> readAll();

    @Query("SELECT * FROM TasksTable WHERE orderId = :orderId" )
    List<TaskResponse> getAllOrderTasks(long orderId);

    @Insert
    public long insert(TaskResponse tasks);

    @Insert
    abstract void insertToTasksList(List<TaskResponse> tasks);

    @Update
    public int update(TaskResponse tasks);

    @Delete
    public int delete(TaskResponse tasks);
}
