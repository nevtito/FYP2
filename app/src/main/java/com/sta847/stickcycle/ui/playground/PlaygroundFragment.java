package com.sta847.stickcycle.ui.playground;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sta847.stickcycle.R;

public class PlaygroundFragment extends Fragment
{

    private PlaygroundViewModel mViewModel;
    private TextView tvPlayground;

    public static PlaygroundFragment newInstance()
    {
        return new PlaygroundFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.playground_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlaygroundViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        tvPlayground = view.findViewById(R.id.tvPlayground);
    }
}

/*

public class TestHere extends ActivityRecognitionClient
{
    void requestActivityTransitionUpdates(final Context context) {
        ActivityTransitionRequest request = buildTransitionRequest();
        // PendingIntent pendingIntent;  // Your pending intent to receive callbacks.
        Task task = ActivityRecognition.getClient(context)
                .requestActivityTransitionUpdates(request, pendingIntent);
        task.addOnSuccessListener(
                new OnSuccessListener() {
                    @Override
                    public void onSuccess(Void result) {
                        // Handle success...
                    }
                });
        task.addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Handle failure...
                    }
                });
      ...
    }

    // Example Transition Request....
    ActivityTransitionRequest buildTransitionRequest() {
        List transitions = new ArrayList<>();
        transitions.add(new ActivityTransition.Builder()
                .setActivityType(DetectedActivity.IN_VEHICLE)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                .build());
        transitions.add(new ActivityTransition.Builder()
                .setActivityType(DetectedActivity.IN_VEHICLE)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                .build());
        transitions.add(new ActivityTransition.Builder()
                .setActivityType(DetectedActivity.WALKING)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                .build());
        transitions.add(new ActivityTransition.Builder()
                .setActivityType(DetectedActivity.WALKING)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                .build());
        return new ActivityTransitionRequest(transitions);
    }

    // Handle the callback intent in your service...
    @Override
    protected void onReceive(Context context, Intent intent) {
        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            for (ActivityTransitionEvent event : result.getTransitionEvents()) {
                // Do something useful here...
            }
        }
    }

}

*/
