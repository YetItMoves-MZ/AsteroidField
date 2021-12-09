package com.example.astroidfield;

import android.app.Application;

public class InitHelper extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        mySharedPreferences.initHelper(this);
    }


}
