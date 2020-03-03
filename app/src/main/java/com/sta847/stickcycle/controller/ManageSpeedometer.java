package com.sta847.stickcycle.controller;

import android.location.Location;

public class ManageSpeedometer extends Location
{
    public ManageSpeedometer(String provider)
    {
        super(provider);
    }

    public ManageSpeedometer(Location l)
    {
        super(l);
    }
}
