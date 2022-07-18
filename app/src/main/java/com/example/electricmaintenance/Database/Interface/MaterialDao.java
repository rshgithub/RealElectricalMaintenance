package com.example.electricmaintenance.Database.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;

import java.util.List;

@Dao
public interface MaterialDao {
    @Query("select * from MaterialsTable")
    public List<MaterialResponse> readAll();

    @Query("SELECT * FROM MaterialsTable WHERE orderId = :orderId" )
    List<MaterialResponse> getAllOrderMaterials(long orderId);

    @Insert
    public long insert(MaterialResponse materials);

    @Insert
    abstract void insertToMaterialsList(List<MaterialResponse> materialsList);

    @Update
    public int update(MaterialResponse materials);

    @Delete
    public int delete(MaterialResponse materials);
}
