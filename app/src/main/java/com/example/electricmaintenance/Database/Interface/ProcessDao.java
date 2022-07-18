package com.example.electricmaintenance.Database.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;

import java.util.List;

@Dao
public interface ProcessDao {
    @Query("select * from ProcessTable")
    public List<ProcessResponse> readAll();

    @Query("SELECT * FROM ProcessTable WHERE orderId = :orderId" )
    List<ProcessResponse> getAllOrderProcesses(long orderId);

    @Insert
    public long insert(ProcessResponse process);

    @Insert
    abstract void insertToProcessList(List<ProcessResponse> processList);

    @Update
    public int update(ProcessResponse process);

    @Delete
    public int delete(ProcessResponse process);
}
