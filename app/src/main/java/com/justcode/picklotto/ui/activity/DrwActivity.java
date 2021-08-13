package com.justcode.picklotto.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.databinding.ActivityDrwBinding;
import com.justcode.picklotto.domain.viewmodel.usecase.DrwUseCase;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwListener;
import com.justcode.picklotto.ui.BaseActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DrwActivity extends BaseActivity {
    public static String TAG = DrwActivity.class.getSimpleName();

    private ActivityDrwBinding mBinding;
    private int currentDrwNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_drw);
        currentDrwNo = finalDrwNo;
        DrwUseCase.getDrwInfo(DrwActivity.this, mDrwListener, currentDrwNo);
        ArrayList<String> drwList = new ArrayList<>();
        for (int i = finalDrwNo; i >= 1; i--) {
            String drwNo = i + "회차";
            drwList.add(drwNo);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                drwList);
        mBinding.spinnerDrw.setAdapter(adapter);

        mBinding.spinnerDrw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.e(TAG, drwList.get(position));
                currentDrwNo = Integer.parseInt(drwList.get(position).replace("회차", ""));
                DrwUseCase.getDrwInfo(DrwActivity.this, mDrwListener, currentDrwNo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    DrwListener mDrwListener = new DrwListener() {
        @Override
        public void onSuccess(DrwEntity entity) {
            setCurrentDrw(entity);
        }

        @Override
        public void onFail(String msg) {
        }
    };

    private void setCurrentDrw(DrwEntity entity) {
        Log.e(TAG, entity.toString());
        mBinding.tvTitle.setText(entity.getDrwNo() + "회 당첨결과");
        mBinding.tvDate.setText(entity.getDrwNoDate());

        BigDecimal a = new BigDecimal(String.valueOf(entity.getFirstAccumamnt()));
        BigDecimal b = new BigDecimal(String.valueOf(entity.getFirstWinamnt()));
        BigDecimal c = new BigDecimal(String.valueOf(100000000));
        BigDecimal total = a.divide(c);
        BigDecimal win = b.divide(c);
        int finalTotal = Integer.parseInt(String.valueOf(Math.round(total.doubleValue())));
        int finalWin = Integer.parseInt(String.valueOf(Math.round(win.doubleValue())));

        mBinding.tvAmntData.setText(finalTotal + "억원 " + "(" + entity.getFirstPrzwnerCo() + "명/" + finalWin +"억)");
        mBinding.icWinContainer.tvNo1.setText(String.valueOf(entity.getDrwtNo1()));
        mBinding.icWinContainer.tvNo2.setText(String.valueOf(entity.getDrwtNo2()));
        mBinding.icWinContainer.tvNo3.setText(String.valueOf(entity.getDrwtNo3()));
        mBinding.icWinContainer.tvNo4.setText(String.valueOf(entity.getDrwtNo4()));
        mBinding.icWinContainer.tvNo5.setText(String.valueOf(entity.getDrwtNo5()));
        mBinding.icWinContainer.tvNo6.setText(String.valueOf(entity.getDrwtNo6()));
        mBinding.icWinContainer.tvNo8.setText(String.valueOf(entity.getBnusNo()));

        int targetNum = 0;
        ImageView img = mBinding.icWinContainer.imgShape1;
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1:
                    targetNum = entity.getDrwtNo1();
                    img = mBinding.icWinContainer.imgShape1;
                    break;
                case 2:
                    targetNum = entity.getDrwtNo2();
                    img = mBinding.icWinContainer.imgShape2;
                    break;
                case 3:
                    targetNum = entity.getDrwtNo3();
                    img = mBinding.icWinContainer.imgShape3;
                    break;
                case 4:
                    targetNum = entity.getDrwtNo4();
                    img = mBinding.icWinContainer.imgShape4;
                    break;
                case 5:
                    targetNum = entity.getDrwtNo5();
                    img = mBinding.icWinContainer.imgShape5;
                    break;
                case 6:
                    targetNum = entity.getDrwtNo6();
                    img = mBinding.icWinContainer.imgShape6;
                    break;
                case 7:
                    targetNum = entity.getBnusNo();
                    img = mBinding.icWinContainer.imgShape8;
                    break;
                default:
                    break;
            }
            if (targetNum <= 10) {
                img.setColorFilter(getResources().getColor(R.color.lotto1));
            } else if (targetNum <= 20) {
                img.setColorFilter(getResources().getColor(R.color.lotto2));
            } else if (targetNum <= 30) {
                img.setColorFilter(getResources().getColor(R.color.lotto3));
            } else if (targetNum <= 40) {
                img.setColorFilter(getResources().getColor(R.color.lotto4));
            } else if (targetNum <= 45) {
                img.setColorFilter(getResources().getColor(R.color.lotto5));
            }
        }
    }
}