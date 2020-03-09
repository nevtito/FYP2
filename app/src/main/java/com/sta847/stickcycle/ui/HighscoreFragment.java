package com.sta847.stickcycle.ui;

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

public class HighscoreFragment extends Fragment
{

   // private HighscoreViewModel highscoreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
      //  highscoreViewModel =
     //           ViewModelProviders.of(this).get(HighscoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_highscore, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
     /*   highscoreViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });*/
        return root;
    }
}