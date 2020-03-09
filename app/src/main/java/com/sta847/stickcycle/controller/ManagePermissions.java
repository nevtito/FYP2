package com.sta847.stickcycle.controller;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.sta847.stickcycle.model.ApplicationConstants;

public class ManagePermissions extends AppCompatActivity
{
    public ManagePermissions()
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getLocationPermission(Activity activity)
    {
        if(activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Log.d("STA847 ", "Already has permission");
        }
        else
        {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ApplicationConstants.LOCATION_REQUEST_CODE);
            Log.d("STA847 ", "First permission granted");
        }
    }
}
