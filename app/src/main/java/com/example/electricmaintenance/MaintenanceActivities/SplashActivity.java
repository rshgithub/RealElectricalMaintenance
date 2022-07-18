package com.example.electricmaintenance.MaintenanceActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricmaintenance.MainActivity;
import com.example.electricmaintenance.SuperClasses.Constraints;
import com.example.electricmaintenance.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

            startActivity(new Intent(SplashActivity.this, DisplayOrdersActivity.class));
            finish();

            }
        }, Constraints.SPLASH_TIME_OUT);
    }

}