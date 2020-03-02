package com.sta847.stickcycle.ui.highscore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HighscoreViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public HighscoreViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}