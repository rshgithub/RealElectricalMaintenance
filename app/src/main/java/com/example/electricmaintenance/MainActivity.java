package com.example.electricmaintenance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.electricmaintenance.MaintenanceActivities.OrderDetailsActivity;
import com.example.electricmaintenance.databinding.AddCarDialogBinding;
import com.example.electricmaintenance.databinding.AddMaterialsDialogBinding;
import com.example.electricmaintenance.databinding.AddTaskDialogBinding;
import com.example.electricmaintenance.databinding.CloseSignalWarningDialogBinding;
import com.example.electricmaintenance.databinding.OrdersFilterDialogBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialogThird(MainActivity.this,"Third ond Custom Dialog");
        }
    });

        btn4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialogFourth(MainActivity.this,"Fourth ond Custom Dialog");
        }
    });

//        btn5.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            showDialogFifth(MainActivity.this,"Fifth ond Custom Dialog");
//        }
//    });
}


    public void showDialogThird(Activity activity, String msg){

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_car_dialog);


        dialog.show();

    }

    public void showDialogFourth(Activity activity, String msg){

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_materials_dialog);


        dialog.show();

    }

//    public void showDialogFifth(Activity activity, String msg){
//
//        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//
//        AddTaskDialogBinding binding = AddTaskDialogBinding.inflate(LayoutInflater.from(this));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(binding.getRoot());
//
//
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.add_task_dialog);
//
//        binding.addTaskCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        binding.addTaskDateOfExecutionET.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//
//                SimpleDateFormat sdf = new SimpleDateFormat(Pickers.myFormat, Locale.US);
//                binding.addTaskDateOfExecutionET.setText(sdf.format(Pickers.myCalendar.getTime()));
//
//            }
//        });
//
////        binding.addTaskStartDateDialog.setOnClickListener(new View.OnClickListener() {
////            @RequiresApi(api = Build.VERSION_CODES.N)
////            @Override
////            public void onClick(View view) {
////
////                SimpleDateFormat sdf = new SimpleDateFormat(Pickers.myFormat, Locale.US);
////                binding.addTaskDateOfExecutionET.setText(sdf.format(Pickers.myCalendar.getTime()));
////
////            }
////        });
//
//        dialog.show();
//
//    }



    public void showAddTaskDialog(Activity activity) {

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        AddTaskDialogBinding binding = AddTaskDialogBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_task_dialog);


        binding.addTaskCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        // -------------------------------------------------------------------------------------

//        binding.addTaskDateOfExecutionET.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//
//                SimpleDateFormat sdf = new SimpleDateFormat(Pickers.myFormat, Locale.US);
//                binding.addTaskDateOfExecutionET.setText(sdf.format(Pickers.myCalendar.getTime()));
//
//            }
//        });
//
//        binding.addTaskStartTimeDialogET.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//        binding.addTaskEndTimeDialogET.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

//        binding.addTaskSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (binding.addTaskTaskET.equals("") || binding.addTaskTaskDetailsET.equals("") || ) {
//                    Toast.makeText(getBaseContext(), "املأ الحقل المطلوب للحفظ", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    // api ------------------------------------------------------------------------------
//
//                }
//            }
//        });
//        dialog.show();
//
//    }
//
//
//
//
//    public void showAddCarDialog(Activity activity){
//        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//
//        AddCarDialogBinding binding = AddCarDialogBinding.inflate(LayoutInflater.from(this));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(binding.getRoot());
//
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.add_car_dialog);
//
//        binding.CarCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        binding.CarSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (binding.CarET.equals("")) {
//                    Toast.makeText(getBaseContext(), "املأ الحقل المطلوب للحفظ", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    // api ------------------------------------------------------------------------------
//
//                }
//            }
//        });
//
//        binding.CarResetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                binding.CarET.getText().clear();
//
//            }
//        });
//
//        dialog.show();
//
//    }
//
//
//    public void showCloseSignalDialog(Context context){
//
//        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//
//        CloseSignalWarningDialogBinding binding = CloseSignalWarningDialogBinding.inflate(LayoutInflater.from(this));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(binding.getRoot());
//
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.close_signal_warning_dialog);
//
//        binding.closeWarningCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        binding.closeWarningAcceptBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        binding.closeWarningCancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((OrderDetailsActivity) context).finish();
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }
//
//
//    public void showAddMaterialDialog(Activity activity){
//
//        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//
//        AddMaterialsDialogBinding binding = AddMaterialsDialogBinding.inflate(LayoutInflater.from(this));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(binding.getRoot());
//
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.add_materials_dialog);
//
//        binding.addMaterialCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        binding.addMaterialClearBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                binding.addMaterialCarET.getText().clear();
//                binding.addMaterialItemNameET.getText().clear();
//                binding.addMaterialItemNumET.getText().clear();
//                binding.addMaterialUnitET.getText().clear();
//                binding.addMaterialUsedQuantityET.getText().clear();
//                binding.addMaterialRecievedQuantityET.getText().clear();
//                binding.addMaterialReturnedQuantityET.getText().clear();
//            }
//        });
//
//        binding.addMaterialSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (binding.addMaterialCarET.equals("")) {
//                    Toast.makeText(getBaseContext(), "املأ حقل اضافة الآلية المطلوب للحفظ", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    // api ------------------------------------------------------------------------------
//
//                }
//            }
//        });
//
//
//        dialog.show();
//
//    }
//
//
//
//    public void showCFilterDialog(Context context, String msg){
//
//        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//        OrdersFilterDialogBinding binding = OrdersFilterDialogBinding.inflate(LayoutInflater.from(this));
//        dialog.setContentView(binding.getRoot());
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.orders_filter_dialog);
//
//        binding.ordersFilterResetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //we can't user for loop to clear all feilds one bc it's not in one single layout , it's nested so we clear every single one alone
//                binding.ordersFilterSERET.getText().clear();
//                binding.ordersFilterSignalNumET.getText().clear();
//                binding.ordersFilterSubscriptionET.getText().clear();
//                binding.ordersFilterPeopleET.getText().clear();
//                //reset spinners
//                binding.ordersFilterAreaSpinner.setSelection(0);
//                binding.ordersFilterStateSpinner.setSelection(0);
//                binding.ordersFilterResidenceSpinner.setSelection(0);
//                binding.ordersFilterMaterialSpinner.setSelection(0);
//                binding.ordersFilterSignalTypeSpinner.setSelection(0);
//            }
//        });
//
//        binding.ordersFilterSearchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//        dialog.show();
//
//    }


    }
}