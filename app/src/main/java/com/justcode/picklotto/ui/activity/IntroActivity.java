package com.justcode.picklotto.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.google.gson.JsonArray;
import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.databinding.ActivityIntroBinding;
import com.justcode.picklotto.domain.listener.PermissionListener;
import com.justcode.picklotto.domain.viewmodel.usecase.DrwUseCase;
import com.justcode.picklotto.domain.viewmodel.usecase.PermissionUseCase;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwListener;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwsListener;
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
        PermissionUseCase.setUserPermission(mContext, mPermissionListener);
        getCurrentDrwbyDate();
    }

    /**
     * Permission 설정
     */
    PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (finalDrwNo == BaseActivity.mDrwViewModel.getCount()) {
                startMainActivity();
            } else {
                DrwUseCase.getDrwsInfo(IntroActivity.this, mDrwsListener);
            }
        }

        @Override
        public void onPermissionDenied() {
            finishAffinity();
            System.exit(0);
        }
    };

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

    /**
     * 업체 설정
     */
    DrwsListener mDrwsListener = new DrwsListener() {
        @SneakyThrows
        @Override
        public void onLoadComplete(JsonArray array) {
            DrwEntity entity = BaseActivity.mDrwViewModel.getLastDrwNo();
            if (entity.getDrwNo() != finalDrwNo) {
                currentDrwNo = entity.getDrwNo();
                DrwUseCase.getDrwInfo(IntroActivity.this, mDrwListener, currentDrwNo);
            } else {
                startMainActivity();
            }
        }

        @Override
        public void onCancelComplete(JsonArray array) {

        }

        @Override
        public void onSetComplete() {

        }
    };

    private void startMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }, 2000);
    }

}