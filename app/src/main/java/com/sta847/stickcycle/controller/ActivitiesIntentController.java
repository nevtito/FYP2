package com.sta847.stickcycle.controller;

import android.app.IntentService;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.sta847.stickcycle.model.ApplicationConstants;

import java.util.List;

public class ActivitiesIntentController extends IntentService
{

    protected static final String TAG = ActivitiesIntentController.class.getSimpleName();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ActivitiesIntentController(String name)
    {
        super(TAG);
    }

    @Override
    public void onHandleIntent(@Nullable Intent intent)
    {
        //Using the given intent extract the result of the service
        ActivityRecognitionResult activityRecognitionResult = ActivityRecognitionResult.extractResult(intent);

        //Get the list of activites and the confidence associated with each one
        List<DetectedActivity> detectedActivityList = activityRecognitionResult.getProbableActivities();

        for (DetectedActivity eachActivity : detectedActivityList)
        {
            Log.e(TAG, "Detected Activity: " + eachActivity.getType() + ", " + eachActivity.getConfidence());
            broadcastActivity(eachActivity);
        }
    }

    private void broadcastActivity(DetectedActivity eachActivity)
    {
        Intent intentToMonitorActivity = new Intent(ApplicationConstants.BROADCAST_DETECTED_ACTIVITY);
        intentToMonitorActivity.putExtra("type", eachActivity.getType());
        intentToMonitorActivity.putExtra("confidence", eachActivity.getConfidence());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentToMonitorActivity);
    }


}
