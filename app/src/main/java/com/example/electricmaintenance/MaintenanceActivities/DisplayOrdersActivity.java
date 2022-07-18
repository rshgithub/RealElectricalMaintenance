package com.example.electricmaintenance.MaintenanceActivities;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.API.ModelsAndResponses.OrderResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Database.DB.DatabaseClient;
import com.example.electricmaintenance.Dialogs.OrdersFilterDialog;
import com.example.electricmaintenance.Interfaces.FilterDialogClick;
import com.example.electricmaintenance.RecyclerViews.Adapters.OrderAdapter;
import com.example.electricmaintenance.databinding.ActivityDisplayOrdersBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayOrdersActivity extends AppCompatActivity {

    private ActivityDisplayOrdersBinding binding;
    private List<MyOrdersData> ordersList;

    OrderAdapter ordersAdapter;
    private ProgressDialog progressDialog;
    private String searchedSubNum , searchedNameN;
    Service service;
    String token;

    boolean connected = false;

    int page = 1, limit = 10;
    boolean isLoading = false;
    boolean isLastPage = true;
    int lastVisibleItem;
    int totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = Creator.getClient().create(Service.class);

        binding = ActivityDisplayOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.displayOrdersAppBar.toolbar);

        showProgress();

        // Internet Connection Check -----------------------------------------------------

//            ordersRecycler();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
//            getOrdersData( page, limit);

        } else {
            Toast.makeText(getBaseContext(), "لا يوجد اتصال بالشبكة", Toast.LENGTH_SHORT).show();
            connected = false;
//            readFromOrdersTable();
        }


        // dummy data -----------------------------------------------------

        ordersList = new ArrayList<>();
        ordersList.add(new MyOrdersData(1,"22/2/22",124994234,"descdescdescdescdescdescdesc","","محمد ابراهيم الشنطي","0599933399","خانيونس","أمل","202020",
                    "آية","طلب تمديد كهرباء","opened"));

        ordersList.add(new MyOrdersData(2,"12/4/22",124994123,"anythinganythinganythinganythinganythinganything","","روان الشوربجي","0599933399","غزة","آية","909090",
                    "أمل","طلب تركيب عداد","closed"));

        ordersAdapter = new OrderAdapter(this,ordersList);
        LinearLayoutManager ordersRecyclerlayoutManager = new LinearLayoutManager(DisplayOrdersActivity.this, RecyclerView.VERTICAL, false);
        binding.displayOrdersRecycler.setLayoutManager(ordersRecyclerlayoutManager);
        binding.displayOrdersRecycler.setHasFixedSize(true);
        binding.displayOrdersRecycler.setAdapter(ordersAdapter);
        ordersAdapter.notifyDataSetChanged();


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ordersList.clear();
                showProgress();
                ordersList = new ArrayList<>();
                getOrdersData(page,limit);

                ordersAdapter = new OrderAdapter(DisplayOrdersActivity.this);

                LinearLayoutManager ordersRecyclerlayoutManager = new LinearLayoutManager(DisplayOrdersActivity.this, RecyclerView.VERTICAL, false);
                binding.displayOrdersRecycler.setLayoutManager(ordersRecyclerlayoutManager);
                binding.displayOrdersRecycler.setHasFixedSize(true);
                binding.displayOrdersRecycler.setAdapter(ordersAdapter);
                ordersAdapter.notifyDataSetChanged();

            }
        });


    binding.displayOrdersAppBar.toolbarFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OrdersFilterDialog(DisplayOrdersActivity.this, new FilterDialogClick() {
                    @Override
                    public void callBack(List<MyOrdersData> list) {
                        ordersAdapter.setData(list);
                    }
                }).show();
            }
        });

        searchedSubNum = binding.displayOrdersSearchBar.searchBarSubscriptionNumET.getText().toString();
        searchedNameN = binding.displayOrdersSearchBar.searchBarPeopleNameET.getText().toString();

        binding.displayOrdersSearchBar.searchBarSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchedSubNum.equals("") && searchedNameN.equals("")) {
                    Toast.makeText(DisplayOrdersActivity.this, "املأ أحد حقول البحث ليتم البحث ", Toast.LENGTH_LONG).show();
                } else {
                    filter(searchedNameN,searchedSubNum);
                }
            }
        });
    }

    private void filter(String name , String num) {
        ordersList = new ArrayList<>();

        if(!name.isEmpty() && !num.isEmpty()){
            for (MyOrdersData item : ordersList) {
                if (item.getUserName().contains(name) && item.getSubscriptionNum().contains(num)) {
                    ordersList.add(item);
                }
            }
        }else if(!name.isEmpty()){
            for (MyOrdersData item : ordersList) {
                if (item.getUserName().contains(name)) {
                    ordersList.add(item);
                }
            }
        }else if(!num.isEmpty()){
            for (MyOrdersData item : ordersList) {
                if (item.getSubscriptionNum().contains(num)) {
                    ordersList.add(item);
                }
            }
        }else{
            Toast.makeText(DisplayOrdersActivity.this, "لا يوجد شيئ بداخل الحقول للبحث", Toast.LENGTH_SHORT).show();
        }
        ordersAdapter.setData(ordersList);
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(DisplayOrdersActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }


    private void getOrdersData(int page , int limit) {

        service.getOrders("Bearer "+token, page, limit).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                if (response.body() != null) {
                    OrderResponse orderResponse = response.body();
                    progressDialog.dismiss();
                    recyclerPagination(orderResponse,page);
                    storeInOrdersTable(orderResponse.getData());
                }

            }
            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(DisplayOrdersActivity.this, "فشل في تحميل البيانات", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }

        });
    }

    private void recyclerPagination(OrderResponse orderResponse , int page){
        if (page == 1) {
            ordersAdapter.setData(orderResponse.getData());
        } else
            ordersAdapter.addToList(orderResponse.getData());
        if (orderResponse.getMeta().getLastPage()==page){
            isLastPage=true;
            Log.e("lastPage",isLastPage+"");
        }
        else{
            isLastPage=false;

        }
    }

    private void storeInOrdersTable(List<MyOrdersData> ordersList){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(DisplayOrdersActivity.this)
                        .getAppDatabase()
                        .ordersDao()
                        .insertToOrdersList(ordersList);
            }
        });
        thread.start();
    }

    private void readFromOrdersTable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ordersList = DatabaseClient
                        .getInstance(DisplayOrdersActivity.this)
                        .getAppDatabase()
                        .ordersDao()
                        .readAll();

                // check if list is null
                if (ordersList.size() != 0) {
                    //this code is to run my code in main thread from the thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            ordersAdapter.addToList(ordersList);
                        }
                    });
                }
            }
        });
        thread.start();
    }

    private void ordersRecycler(){
        ordersList = new ArrayList<>();
        ordersAdapter = new OrderAdapter(this);
        LinearLayoutManager ordersRecyclerlayoutManager = new LinearLayoutManager(DisplayOrdersActivity.this, RecyclerView.VERTICAL, false);
        binding.displayOrdersRecycler.setLayoutManager(ordersRecyclerlayoutManager);
        binding.displayOrdersRecycler.setHasFixedSize(true);

        binding.displayOrdersRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                Log.e("totalItemCount", totalItemCount + "");
                Log.e("lastVisibleItem", lastVisibleItem + "");
                if (lastVisibleItem == (totalItemCount - 1) && !isLoading && totalItemCount != 0 && !isLastPage) {
                    page++;
                    getOrdersData( page, limit);
                    Log.e("listSize", "done");
                }
            }
        });

        binding.displayOrdersRecycler.setAdapter(ordersAdapter);
        ordersAdapter.notifyDataSetChanged();
    }
}