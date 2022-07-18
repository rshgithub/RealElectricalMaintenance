package com.example.electricmaintenance.SuperClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.electricmaintenance.API.Service;

public class Constraints {

    public static final int SPLASH_TIME_OUT = 1500;

    public static final String FILTERED_DATA = "FilteredData";
    public static final int FILTERED_DATA_VAL = 0;

    public static final String ORDER_ID = "order_id";
    public static final int ORDER_ID_VAL = 0;


    public static void showProgress(Context context) {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }


}
