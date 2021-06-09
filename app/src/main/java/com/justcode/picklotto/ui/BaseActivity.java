package com.justcode.picklotto.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.justcode.picklotto.BaseApplication;
import com.justcode.picklotto.domain.viewmodel.db.DrwViewModel;
import com.justcode.picklotto.domain.viewmodel.db.StatisticsViewModel;

public class BaseActivity  extends AppCompatActivity implements ViewModelStoreOwner {

    private static final String TAG = BaseActivity.class.getSimpleName();

    public BaseApplication mApp;
    public static BaseActivity mContext;
    public LayoutInflater mInflater;
    private ViewModelProvider.AndroidViewModelFactory mViewModelFactory;
    public static DrwViewModel mDrwViewModel;
    public static StatisticsViewModel mStatisticsViewModel;
    public static int finalDrwNo;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(mContext);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mViewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        mDrwViewModel = new ViewModelProvider(this, mViewModelFactory).get(DrwViewModel.class);
        mStatisticsViewModel = new ViewModelProvider(this, mViewModelFactory).get(StatisticsViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public DrwViewModel getDrwViewModel() {
        return mDrwViewModel;
    }

}
