package com.justcode.picklotto.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.StatisticsEntity;
import com.justcode.picklotto.data.vo.StatisticsVo;
import com.justcode.picklotto.databinding.ActivityStatisticsBinding;
import com.justcode.picklotto.domain.viewmodel.usecase.DrwUseCase;
import com.justcode.picklotto.ui.BaseActivity;
import com.justcode.picklotto.ui.adapter.StatisticsAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class StatisticsActivity extends BaseActivity {
    public static String TAG = StatisticsActivity.class.getSimpleName();

    private ActivityStatisticsBinding mBinding;

    private List mStatisticsList;
    private List mStatisticsBonusList;

    private StatisticsAdapter mAdapter;
    private int mMenu1 = 0;
    private Boolean mSort = true;

    @SneakyThrows
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);

        mStatisticsList = mDrwViewModel.getStatistics();
        Log.e(TAG, "statisticsList : " + mStatisticsList.toString());

        mStatisticsBonusList = mDrwViewModel.getStatisticsBouns();
        Log.e(TAG, "statisticsBonusList : " + mStatisticsBonusList.toString());

        Description description = new Description();
        description.setText("");

        mAdapter = new StatisticsAdapter(this);
        mBinding.rvStatistics.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.rvStatistics.setAdapter(mAdapter);

        insertStatistics(0, mSort);

        ArrayList<String> selectList = new ArrayList<>();
        selectList.add("보너스볼 미포함");
        selectList.add("보너스볼 포함");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                selectList);
        mBinding.spinnerStatistics.setAdapter(adapter);

        mBinding.spinnerStatistics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.e(TAG, "position : " + position);
                mMenu1 = position;
                insertStatistics(mMenu1, mSort);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        ArrayList<String> totalList = new ArrayList<>();
        totalList.add("번호순서");
        totalList.add("당첨많은순서");
        ArrayAdapter<String> totalAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                totalList);
        mBinding.spinnerStatisticsTotal.setAdapter(totalAdapter);

        mBinding.spinnerStatisticsTotal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.e(TAG, "position : " + position);
                if (position == 0) {
                    mSort = true;
                } else if (position == 1) {
                    mSort = false;
                }
                insertStatistics(mMenu1, mSort);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    @SneakyThrows
    private void insertStatistics(int menuPos, Boolean flag) {
        mStatisticsViewModel.deleteAll();
        List insertList;
        if (menuPos == 0) {
            insertList = mStatisticsList;
        } else {
            insertList = mStatisticsBonusList;
        }
        for(int i = 1 ; i <= insertList.size() ; i++) {
            StatisticsEntity entity = new StatisticsEntity();
            int count = (int) insertList.get(i-1);
            entity.setBallNumber(i);
            entity.setCount(count);
            mStatisticsViewModel.insert(entity);
        }
        if (flag) {
            mAdapter.setItems(mStatisticsViewModel.getAllByBallNumber());
        } else {
            mAdapter.setItems(mStatisticsViewModel.getAllByCount());
        }
    }

}