package com.sta847.stickcycle.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.sta847.stickcycle.MainActivity;
import com.sta847.stickcycle.model.ApplicationConstants;

public class ManageSpeedometer implements LocationListener, GoogleApiClient.ConnectionCallbacks
{
    private Float speed;
    private Double latitude;
    private Double longitude;
    private LocationManager locationManager;
    private MainActivity mainActivity;
    private Location lastLocation;
    private Double distanceTraveled;
    private Double distanceBetweenReadings;
    private boolean isRunning;

    //experiment
    private double accuracy;

    public ManageSpeedometer(MainActivity mainActivity)
    {
        super();
        this.mainActivity = mainActivity;
        this.locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);
        this.speed = 0.0f;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.distanceTraveled = 0.0;
        this.lastLocation = null;
        this.distanceBetweenReadings = 0.0;
        this.isRunning = false;

        //experiment
        this.accuracy = 0.0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onLocationChanged(Location newLocation)
    {
        if (isRunning)
        {
            if (newLocation != null)
            {
                speed = newLocation.getSpeed() * 3.6f;
                latitude = newLocation.getLatitude();
                longitude = newLocation.getLongitude();

                //experiment
                accuracy = newLocation.getAccuracy();

                if (lastLocation == null)
                {
                    lastLocation = newLocation;
                    distanceTraveled = 0.0;
                    distanceBetweenReadings = 0.0;
                } else
                {
                    if(newLocation.getAccuracy() <= 5.0)
                    {
                        distanceTraveled = distanceTraveled + (lastLocation.distanceTo(newLocation));
                        distanceBetweenReadings = Double.valueOf(lastLocation.distanceTo(newLocation));
                        lastLocation = newLocation;

                        Log.d("STA847: ManSpeed", "Accuracy is " + newLocation.getAccuracy());
                    }
                }
                Log.d("sta847: ManSpeed", "newLocation is not null " + newLocation.getSpeed() + " " + newLocation.getLatitude());
            } else
            {
                speed = 0.0f;
                Log.d("sta847: ManSpeed", "For some reason the newLocation is null");
            }
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

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Location getLastLocation()
    {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation)
    {
        this.lastLocation = lastLocation;
    }

    public Double getDistanceTraveled()
    {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Double distanceTraveled)
    {
        this.distanceTraveled = distanceTraveled;
    }

    public void startSpeedometer()
    {
        isRunning = true;
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
                Log.d("STA847: ", "Manage speedometer does not have permission");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ApplicationConstants.MINIMUM_LOCATION_TIME, ApplicationConstants.MINIMUM_LOCATION_DISTANCE, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,ApplicationConstants.MINIMUM_LOCATION_TIME,ApplicationConstants.MINIMUM_LOCATION_DISTANCE,this);
            Log.d("STA847 ", "Request update for locations");
        }
    }

    public void stopSpeedometer()
    {
        this.isRunning = false;
        this.speed = 0.0f;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.distanceTraveled = 0.0;
        this.lastLocation = null;
        this.distanceBetweenReadings = 0.0;
        this.locationManager.removeUpdates(this);
        Log.d("STA847: ", "Stop update for locations");
    }

    public void pauseSpeedometer()
    {

    }

    public Double getDistanceBetweenReadings()
    {
        return distanceBetweenReadings;
    }

    public void setDistanceBetweenReadings(Double distanceBetweenReadings)
    {
        this.distanceBetweenReadings = distanceBetweenReadings;
    }

    public void setRunning(boolean running)
    {
        isRunning = running;
    }

    //experiment
    public double getAccuracy()
    {
        return accuracy;
    }

    public void setAccuracy(double accuracy)
    {
        this.accuracy = accuracy;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }
}
