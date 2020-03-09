package com.sta847.stickcycle.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.sta847.stickcycle.MainActivity;
import com.sta847.stickcycle.model.ApplicationConstants;

public class ManageSpeedometer implements LocationListener
{
    private Float speed;
    private LocationManager locationManager;
    private MainActivity mainActivity;

    public ManageSpeedometer(MainActivity mainActivity)
    {
        super();
        this.mainActivity = mainActivity;
        locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);
        speed = 0.0f;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setupRequirements()
    {
        if (locationManager != null)
        {
            if (ActivityCompat.checkSelfPermission(mainActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d("STA847 ", "Manage speedometer does not have permission");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ApplicationConstants.MINIMUM_LOCATION_TIME, ApplicationConstants.MINIMUM_LOCATION_DISTANCE, this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            {
                while(!locationManager.isLocationEnabled())
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ApplicationConstants.MINIMUM_LOCATION_TIME, ApplicationConstants.MINIMUM_LOCATION_DISTANCE, this);
                }
            }
            Log.d("STA847 ", "Has permission therefore ask location update");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onLocationChanged(Location location)
    {
        if (location != null)
        {
            speed = location.getSpeed();
        }
        else
        {
            speed = 0.0f;
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    public Float getSpeed()
    {
        return speed;
    }

    public void setSpeed(Float speed)
    {
        this.speed = speed;
    }
}
