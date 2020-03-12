package com.sta847.stickcycle.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
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
    private Double latitude;
    private Double longitude;
    private LocationManager locationManager;
    private MainActivity mainActivity;
    private Location lastLocation;
    private Double distanceTraveled;
    private Double distanceBetweenReadings;
    private boolean isRunning;
    private double radiusPrecision;
    private Criteria criteria;

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
        this.radiusPrecision = 0.0;
        this.criteria = new Criteria();
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

                if (lastLocation == null)
                {
                    lastLocation = newLocation;
                    distanceTraveled = 0.0;
                    distanceBetweenReadings = 0.0;
                } else
                {
                    if(newLocation.getAccuracy() <= radiusPrecision)
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
        else
        {
            //what to if it is not running.
            Log.d("STA847 ManSpeed", "Incomplete");
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
        this.radiusPrecision = 0.0;
        Log.d("STA847: ", "Stop update for locations");
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

    public double getRadiusPrecision()
    {
        return radiusPrecision;
    }

    public void setRadiusPrecision(double radiusPrecision)
    {
        this.radiusPrecision = radiusPrecision;
    }

    public void pauseSpeedometer()
    {

    }

    private void setCriteriaFineAccuracy()
    {
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setAltitudeRequired(true);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);
        criteria.setBearingRequired(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
    }
}
