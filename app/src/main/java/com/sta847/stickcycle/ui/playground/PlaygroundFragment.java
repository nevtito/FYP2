package com.sta847.stickcycle.ui.playground;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sta847.stickcycle.R;

public class PlaygroundFragment extends Fragment
{

    private PlaygroundViewModel PlaygroundViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        PlaygroundViewModel =
                ViewModelProviders.of(this).get(PlaygroundViewModel.class);
        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        PlaygroundViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }
}