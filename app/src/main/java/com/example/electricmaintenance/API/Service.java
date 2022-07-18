package com.example.electricmaintenance.API;


import com.example.electricmaintenance.API.ModelsAndResponses.AddNewTaskResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.Area;
import com.example.electricmaintenance.API.ModelsAndResponses.AreaResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.Example;
import com.example.electricmaintenance.API.ModelsAndResponses.FilterResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewMaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.AddNewProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.LocationResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.API.ModelsAndResponses.OrderResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.SignalTypeResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.State;
import com.example.electricmaintenance.API.ModelsAndResponses.StateResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TransferorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  Service {

     String ENDPOINT = "https://fakestoreapi.com/";

    // display orders activity ------------------------------------------------------------------
    @GET("orders")
    Call<OrderResponse> getOrders(@Header("Authorization") String token , @Query("page") int page , @Query("limit") int limit);

    // order details activity --------------------------------------------------------------------
    @GET("orders/{id}")
    Call<MyOrdersData> getOrderDetails(@Path("id")int id , @Header("Authorization") String token);

    @GET("orders/{id}")
    Call<List<MaterialResponse>> getOrderUsedMaterials(@Path("id")int id ,@Header("Authorization") String token);

    @GET("orders/{id}")
    Call<List<MaterialResponse>> getOrderReturnedMaterials(@Path("id")int id ,@Header("Authorization") String token);

    @GET("orders/{id}")
    Call<List<TaskResponse>> getOrderTasks(@Path("id")int id, @Header("Authorization") String token);

    @GET("orders/{id}")
    Call<List<ProcessResponse>> getOrderProcesses(@Path("id")int id ,@Header("Authorization") String token);

    // Spinners ----------------------------------------------------------------------------------

    @GET("location")
    Call<LocationResponse> getLocation( @Header("Authorization") String token);

    @GET("signalType")
    Call<SignalTypeResponse> getSignalType( @Header("Authorization") String token);

    @GET("state")
    Call<Example> getState(@Header("Authorization") String token);

    // dialogs----------------------------------------------------------------------------------

    @POST("newProcess")
    Call<List<ProcessResponse>> postNewProcess(@Body AddNewProcessResponse processModel , @Header("Authorization") String token);

    @POST("newMaterial")
    Call<List<MaterialResponse>> postNewMaterial(@Body AddNewMaterialResponse materialModel , @Header("Authorization") String token);

    @POST("newTask")
    Call<List<TaskResponse>> postNewTask(@Body AddNewTaskResponse taskModel , @Header("Authorization") String token);

    @POST("filter")
    Call<List<MyOrdersData>> postFilter(@Body FilterResponse filterModel , @Header("Authorization") String token);

    @PUT("orderStatus/{id}")
    Call<OrderResponse> orderStatus(@Path("id") int id , @Header("Authorization") String token, @Query("status") String status);


}
