package com.justcode.picklotto.ui.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.databinding.ActivityIntroBinding;
import com.justcode.picklotto.domain.viewmodel.usecase.DrwUseCase;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwListener;
import com.justcode.picklotto.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;

public class IntroActivity extends BaseActivity {
    public static String TAG = IntroActivity.class.getSimpleName();

    private ActivityIntroBinding mBinding;
    private int currentDrwNo = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        if (mDrwViewModel.getCount() == 0) {
            DrwUseCase.getDrwInfo(this, mDrwListener, currentDrwNo);
        } else {
            startMainActivity();
        }
        getCurrentDrwbyDate();
    }

    @SneakyThrows
    public void getCurrentDrwbyDate() {
        String startDate = "2002-12-07 23:59:59";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date cDate = new Date();
        Date sDate = dateFormat.parse(startDate);
        long diff = cDate.getTime() - sDate.getTime();
        long currentEpi = (diff / (86400 * 1000 * 7)) + 1;
        finalDrwNo = (int) currentEpi;
    }

    DrwListener mDrwListener = new DrwListener() {
        @Override
        public void onSuccess(DrwEntity entity) {
            currentDrwNo++;
            DrwUseCase.getDrwInfo(IntroActivity.this, mDrwListener, currentDrwNo);
        }

        @Override
        public void onFail(String msg) {
            startMainActivity();
        }
    };

    private void startMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }, 2000);
    }

}