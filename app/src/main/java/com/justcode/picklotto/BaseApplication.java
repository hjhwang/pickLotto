package com.justcode.picklotto;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.multidex.MultiDexApplication;

import com.justcode.picklotto.data.repository.AppDatabase;

public class BaseApplication extends MultiDexApplication {

    private static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication mInstance;
    private static Context mAppContext;
    public static AppDatabase mDb;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
        mDb = AppDatabase.getDatabase(mAppContext);

        IntentFilter intentFilters = new IntentFilter();
        intentFilters.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilters.addAction(Intent.ACTION_SCREEN_ON);
        intentFilters.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilters.addAction(Intent.ACTION_MANAGE_NETWORK_USAGE);
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
    private void setAppContext(Context mAppContext) {
        BaseApplication.mAppContext = mAppContext;
    }
    public static Context getAppContext() {
        return mAppContext;
    }

    public static void finishAll() {
        System.runFinalization();
        System.exit(0);
    }
}
