package com.sta847.stickcycle.ui.graphics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GraphicsViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public GraphicsViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is graphics fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}