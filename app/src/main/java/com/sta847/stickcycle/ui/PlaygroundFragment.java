package com.sta847.stickcycle.ui;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.LocationListener;
import com.sta847.stickcycle.MainActivity;
import com.sta847.stickcycle.R;
import com.sta847.stickcycle.controller.ManageSpeedometer;

public class PlaygroundFragment extends Fragment implements LocationListener
{
    private ManageSpeedometer manageSpeedometer;
    private Float speed;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
       View root = inflater.inflate(R.layout.playground_fragment, container, false);
       manageSpeedometer = ((MainActivity) this.getActivity()).getManageSpeedometer();

       Log.d("STA847 playground" , manageSpeedometer.getSpeed() + "");
       textView = root.findViewById(R.id.tvPlayground);
       speed = manageSpeedometer.getSpeed();
       textView.setText(speed + "");
       return root;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        speed = manageSpeedometer.getSpeed();
        textView.setText(speed + "");

        Log.d("STA847 " , manageSpeedometer.getSpeed() + "");
    }
}