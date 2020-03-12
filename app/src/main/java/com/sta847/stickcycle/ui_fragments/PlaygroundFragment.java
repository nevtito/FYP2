package com.sta847.stickcycle.ui_fragments;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.sta847.stickcycle.MainActivity;
import com.sta847.stickcycle.R;
import com.sta847.stickcycle.controller.ManageSpeedometer;
import java.util.Locale;

public class PlaygroundFragment extends Fragment implements Runnable
{
    private ManageSpeedometer manageSpeedometer;
    private TextView textView;
    private Button startButton, stopButton;
    private Context context;

    private String temporary;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
       View root = inflater.inflate(R.layout.playground_fragment, container, false);
       try
       {
           manageSpeedometer = ((MainActivity) this.getActivity()).getManageSpeedometer();
           context = this.getActivity().getApplicationContext();

           startButton = root.findViewById(R.id.startButton);
           stopButton = root.findViewById(R.id.stopButton);

           startButton.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v)
               {
                   manageSpeedometer.startSpeedometer();
               }
           });
           stopButton.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v)
               {
                   manageSpeedometer.stopSpeedometer();
               }
           });

           textView = root.findViewById(R.id.tvPlayground);
           textView.setText(String.format((Locale.UK),"Speed is %.4f Km/h" +
                           "\n Latitude is %.1f" +
                           "\n Longitude is %.1f" +
                           "\n Distance is %.4f Km" +
                           "\n DistanceRead is %.4f Km" +
                           "\n Accuracy is %%.0f m",
                   manageSpeedometer.getSpeed(),
                   manageSpeedometer.getLatitude(),
                   manageSpeedometer.getLongitude(),
                   (manageSpeedometer.getDistanceTraveled()/1000),
                   (manageSpeedometer.getDistanceBetweenReadings()/1000),
                   manageSpeedometer.getRadiusPrecision()));

           Runnable target;
           Thread runSpeedometer = new Thread(this);
           runSpeedometer.start();
       }
       catch(Exception exception)
       {
           Log.d("STA847: Playground", "Exception has been thrown " + exception.toString());
       }
       return root;

    }



    @Override
    public void run()
    {
        Looper.prepare();
        while(true)
        {
            textView.setText(String.format((Locale.UK),"Speed is %.1f Km/h" +
                            "\n Latitude is %.1f" +
                            "\n Longitude is %.1f" +
                            "\n Distance is %.1f Km" +
                            "\n DistanceRead is %.1f m" +
                            "\n Accuracy is %.0f m",
                    manageSpeedometer.getSpeed(),
                    manageSpeedometer.getLatitude(),
                    manageSpeedometer.getLongitude(),
                    (manageSpeedometer.getDistanceTraveled()/1000),
                    (manageSpeedometer.getDistanceBetweenReadings()/1000),
                    manageSpeedometer.getRadiusPrecision()));
            Log.d("STA847: playground ",  manageSpeedometer.getSpeed().toString());
            Log.d("STA847: accuracy ",  Double.toString(manageSpeedometer.getRadiusPrecision()));
        }
    }
}