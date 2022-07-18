package com.example.electricmaintenance.MaintenanceActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.bumptech.glide.Glide;
import com.example.electricmaintenance.API.Creator;
import com.example.electricmaintenance.API.ModelsAndResponses.MyOrdersData;
import com.example.electricmaintenance.API.ModelsAndResponses.MaterialResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.ProcessResponse;
import com.example.electricmaintenance.API.ModelsAndResponses.TaskResponse;
import com.example.electricmaintenance.API.Service;
import com.example.electricmaintenance.Database.DB.DatabaseClient;
import com.example.electricmaintenance.Dialogs.AddProcessDialog;
import com.example.electricmaintenance.Dialogs.AddMaterialDialog;
import com.example.electricmaintenance.Dialogs.AddTaskDialog;
import com.example.electricmaintenance.Dialogs.CloseSignalDialog;
import com.example.electricmaintenance.Interfaces.CloseSingalDialogClick;
import com.example.electricmaintenance.Interfaces.MaterialDialogClick;
import com.example.electricmaintenance.Interfaces.ProcessDialogClick;
import com.example.electricmaintenance.Interfaces.TaskDialogClick;
import com.example.electricmaintenance.R;
import com.example.electricmaintenance.RecyclerViews.Adapters.MaterialAdapter;
import com.example.electricmaintenance.RecyclerViews.Adapters.ProcessAdapter;
import com.example.electricmaintenance.RecyclerViews.Adapters.TaskAdapter;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.ActivityOrderDetailsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    private ActivityOrderDetailsBinding binding;
    int orderId ;
    String orderStatus ;
    ProgressDialog progressDialog ;
    List<ProcessResponse> processesList;
    List<TaskResponse> tasksList;
    List<MaterialResponse> materialsList;
    ProcessAdapter processAdapter;
    MaterialAdapter materialAdapter;
    TaskAdapter taskAdapter;
    LinearLayoutManager processesManager , tasksManager , materialsManager ;
    Service service;
    String token;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        service = Creator.getClient().create(Service.class);

        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.orderDetailsUserInfoCard.ordersCardOptionsBtn.setVisibility(View.GONE);
        setSupportActionBar(binding.orderDetailsAppBar.toolbar);

        showProgress();
        //----------------------------------------------------------------------

        if (getIntent() != null) {
            orderId = getIntent().getIntExtra(Constraints.ORDER_ID,Constraints.ORDER_ID_VAL);
            getOrderDetails(orderId);
        }else{
            Toast.makeText(OrderDetailsActivity.this, "لا يوجد بيانات", Toast.LENGTH_LONG).show();
        }

        // Internet Connection Check -----------------------------------------------------

//        tasksRecycler();
//        processesRecycler();
//        usedMaterialsRecycler();
//        returnedMaterialsecycler();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
//            getOrdersData( page, limit);

        } else {
            Toast.makeText(getBaseContext(), "لا يوجد اتصال بالشبكة", Toast.LENGTH_SHORT).show();
            connected = false;
//            readProcesses(orderId);
//            readTasks(orderId);
//            readMaterials(orderId);
        }

        //Tasks----------------------------------------------------------------------
        tasksList = new ArrayList<>();

        tasksList.add(new TaskResponse(14543213,"تمديد خطوط كهرباء","descriptiondescscriptiondescriptiondescriptiondescription","14/5/22","11:00 AM","12:00 PM","ساعه","روان","14/5/22"));
//        tasksList.add(new TaskResponse(2,"امداد","descdescdescdesc","12/5/22","11:00 AM","12:00 PM","2ساعه","روان","19/2/22"));

        taskAdapter = new TaskAdapter(this,tasksList);
        tasksManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.orderDetailsTasksRecycler.setLayoutManager(tasksManager);
        binding.orderDetailsTasksRecycler.setHasFixedSize(true);
        binding.orderDetailsTasksRecycler.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();


        //Processes----------------------------------------------------------------------
        processesList = new ArrayList<>();

        processesList.add(new ProcessResponse(1,"22/2/22","روان مصطفى","العملية الاولى"));
        processesList.add(new ProcessResponse(2,"14/5/22","ايه انشاصي","العملية الثانية"));

        processAdapter = new ProcessAdapter(this,processesList);
        processesManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.orderDetailsProcessRecycler.setLayoutManager(processesManager);
        binding.orderDetailsProcessRecycler.setHasFixedSize(true);
        binding.orderDetailsProcessRecycler.setAdapter(processAdapter);
        processAdapter.notifyDataSetChanged();



        // Materials----------------------------------------------------------------------

        materialsList = new ArrayList<>();

        materialsList.add(new MaterialResponse(2323431,213,2,30,12,10,"30 شيكل","المادة الأولى","جديد","روان مصطفى","22/2/22"));
        materialsList.add(new MaterialResponse(34234,23,4,20,9,10,"50 شيكل","المادة الثانية","مستخدم","آية مصطفى","24/4/22"));

        materialAdapter = new MaterialAdapter(this,materialsList);
        materialsManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.orderDetailsMaterialsRecycler.setLayoutManager(materialsManager);
        binding.orderDetailsMaterialsRecycler.setHasFixedSize(true);
        binding.orderDetailsMaterialsRecycler.setAdapter(materialAdapter);
        materialAdapter.notifyDataSetChanged();

        //---------------------------------------------------------------------------------------------

        binding.orderDetailsAppBar.toolbarCloseSignalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CloseSignalDialog(OrderDetailsActivity.this,orderId, new CloseSingalDialogClick() {
                    @Override
                    public void callBack() {
                        orderClosedStatusSetting();
                    }
                }).show();
            }
        });

        binding.orderDetailsAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddTaskDialog(OrderDetailsActivity.this, new TaskDialogClick() {
                    @Override
                    public void callBack(List<TaskResponse> list) {
                     taskAdapter.setData(list);
                    }
                }).show();
            }
        });

        binding.orderDetailsAddProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddProcessDialog(OrderDetailsActivity.this, new ProcessDialogClick() {
                    @Override
                    public void callBack(List<ProcessResponse> list) {
                        processAdapter.setData(list);
                    }
                }).show();
            }
        });

        binding.orderDetailsAddMaterialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddMaterialDialog(OrderDetailsActivity.this, new MaterialDialogClick() {
                    @Override
                    public void callBack(List<MaterialResponse> list) {
                        materialAdapter.setData(list);
                    }
                }).show();
            }
        });

    }

    private void getOrderDetails(int orderId) {
        service.getOrderDetails(orderId,"Bearer "+token).enqueue(new Callback<MyOrdersData>() {
            @Override
            public void onResponse(Call<MyOrdersData> call, Response<MyOrdersData> response) {
                if (response.body() != null) {

                    binding.orderDetailsUserInfoCard.ordersCardUserName.setText(response.body().getUserName());
                    binding.orderDetailsUserInfoCard.ordersCardUserPhoneNum.setText(response.body().getUserPhone());
                    binding.orderDetailsUserInfoCard.ordersCardDescription.setText(response.body().getDescription());
                    binding.orderDetailsUserInfoCard.ordersCardEntryDate.setText(response.body().getEntryDate());
                    binding.orderDetailsUserInfoCard.ordersCardSerNum.setText(response.body().getSerNumber());
                    binding.orderDetailsUserInfoCard.ordersCardTransfarerTV.setText(response.body().getTransferorName());
                    binding.orderDetailsUserInfoCard.ordersCardUserStateTV.setText(response.body().getUserState());
                    binding.orderDetailsUserInfoCard.ordersCardEntryNameTV.setText(response.body().getEntryUserName());
                    binding.orderDetailsUserInfoCard.ordersCardSignalTypeTV.setText(response.body().getSignalType());
                    binding.orderDetailsUserInfoCard.ordersCardSubscriptionNoTV.setText(response.body().getSubscriptionNum());

                    if(response.body().getStatus().equals("closed")){ orderClosedStatusSetting();}

                    if (getApplicationContext() != null) {
                        Glide.with(getApplicationContext()).load(response.body().getUserImage()).circleCrop().into(binding.orderDetailsUserInfoCard.ordersCardUserImage);
                    } else {
                        binding.orderDetailsUserInfoCard.ordersCardUserImage.setImageResource(R.drawable.ic_user);
                    }

                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<MyOrdersData> call, Throwable t) {  t.printStackTrace(); }


        });
    }

    private void orderClosedStatusSetting() {

        binding.orderDetailsAppBar.toolbarCloseSignalButton.setText(R.string.order_closed);
        binding.orderDetailsAppBar.toolbarCloseSignalButton.setTextColor(getResources().getColor(R.color.BlackText));
        binding.orderDetailsAppBar.toolbarCloseSignalButton.setBackgroundColor(getResources().getColor(R.color.LightGrey));
        binding.orderDetailsAppBar.toolbarCloseSignalButton.setEnabled(false);
        binding.orderDetailsAddProcessBtn.setEnabled(false);
        binding.orderDetailsAddMaterialsBtn.setEnabled(false);
        binding.orderDetailsAddTaskBtn.setEnabled(false);

    }

    private void getOrderMaterials() {
        service.getOrderUsedMaterials(orderId,"Bearer "+token).enqueue(new Callback<List<MaterialResponse>>() {
            @Override
            public void onResponse(Call<List<MaterialResponse>> call, Response<List<MaterialResponse>> response) {
                if (response.body() != null) {
                    materialsList = response.body();
                    materialAdapter.setData(materialsList);
                    storeInUserMaterials(materialsList);
                }
            }
            @Override
            public void onFailure(Call<List<MaterialResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getOrderProcesses() {
        service.getOrderProcesses(orderId,"Bearer "+token).enqueue(new Callback<List<ProcessResponse>>() {
            @Override
            public void onResponse(Call<List<ProcessResponse>> call, Response<List<ProcessResponse>> response) {
                if (response.body() != null) {
                    processesList = response.body();
                    processAdapter.setData(processesList);
                    storeInUserProcesses(processesList);
                }
            }
            @Override
            public void onFailure(Call<List<ProcessResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getOrderTasks() {
        service.getOrderTasks(orderId,"Bearer "+token).enqueue(new Callback<List<TaskResponse>>() {
            @Override
            public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                if (response.body() != null) {
                    tasksList = response.body();
                    taskAdapter.setData(tasksList);
                    storeInUserTasks(tasksList);
                }
            }

            @Override
            public void onFailure(Call<List<TaskResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    private void storeInUserMaterials(List<MaterialResponse> materialsList){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .materialDao()
                        .insertToMaterialsList(materialsList);
            }
        });
        thread.start();
    }

    private void storeInUserProcesses(List<ProcessResponse> processesList){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .processDao()
                        .insertToProcessList(processesList);
            }
        });
        thread.start();
    }

    private void storeInUserTasks(List<TaskResponse> tasksList){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .tasksDao()
                        .insertToTasksList(tasksList);
            }
        });
        thread.start();
    }


    private void readTasks(long orderId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                tasksList = DatabaseClient
                        .getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .tasksDao()
                        .getAllOrderTasks(orderId);

                // check if list is null
                if (tasksList.size() != 0) {
                    //this code is to run my code in main thread from the thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            taskAdapter.addToList(tasksList);
                        }
                    });
                }
            }
        });
        thread.start();
    }


    private void readMaterials(long orderId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                materialsList = DatabaseClient
                        .getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .materialDao()
                        .getAllOrderMaterials(orderId);

                // check if list is null
                if (materialsList.size() != 0) {
                    //this code is to run my code in main thread from the thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            materialAdapter.addToList(materialsList);
                        }
                    });
                }
            }
        });
        thread.start();
    }

    private void readProcesses(long orderId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                processesList = DatabaseClient
                        .getInstance(OrderDetailsActivity.this)
                        .getAppDatabase()
                        .processDao()
                        .getAllOrderProcesses(orderId);

                // check if list is null
                if (processesList.size() != 0) {
                    //this code is to run my code in main thread from the thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            processAdapter.addToList(processesList);
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void processesRecycler(){
        processesList = new ArrayList<>();
        processAdapter = new ProcessAdapter(this);
        processesManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.orderDetailsProcessRecycler.setLayoutManager(processesManager);
        binding.orderDetailsProcessRecycler.setHasFixedSize(true);
        binding.orderDetailsProcessRecycler.setAdapter(processAdapter);
        processAdapter.notifyDataSetChanged();
        getOrderProcesses();
    }

    public void tasksRecycler(){
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this);
        tasksManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.orderDetailsTasksRecycler.setLayoutManager(tasksManager);
        binding.orderDetailsTasksRecycler.setHasFixedSize(true);
        binding.orderDetailsTasksRecycler.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
        getOrderTasks();

    }

    public void usedMaterialsRecycler(){
        materialsList = new ArrayList<>();
        materialAdapter = new MaterialAdapter(this);
        materialsManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.orderDetailsMaterialsRecycler.setLayoutManager(materialsManager);
        binding.orderDetailsMaterialsRecycler.setHasFixedSize(true);
        binding.orderDetailsMaterialsRecycler.setAdapter(materialAdapter);
        materialAdapter.notifyDataSetChanged();
        getOrderMaterials();

    }


}