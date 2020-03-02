package com.sta847.stickcycle.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundActivityDetectorController extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
