package com.sta847.stickcycle.ui.playground;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaygroundViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public PlaygroundViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is Maps fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}