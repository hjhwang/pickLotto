package com.justcode.picklotto.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.databinding.ActivityRandomBinding;
import com.justcode.picklotto.ui.BaseActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class RandomActivity extends BaseActivity implements View.OnClickListener {
    public static String TAG = RandomActivity.class.getSimpleName();

    private ActivityRandomBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_random);

        mBinding.winContainer.getRoot().setVisibility(View.INVISIBLE);
        mBinding.btnSave.setVisibility(View.INVISIBLE); // 내 번호 저장 나중에 추가 작업
        mBinding.btnSave.setOnClickListener(this);
        mBinding.btnRandom1.setOnClickListener(this);
        mBinding.btnRandom2.setOnClickListener(this);
        mBinding.btnRandom3.setOnClickListener(this);
        mBinding.btnRandom4.setOnClickListener(this);
    }

    private TreeSet getRandomArr() {
        //중복 제거, 오름차순 정렬을 위해서 TreeSet 사용
        TreeSet<Integer> set = new TreeSet(); //TreeSet의 사이즈가 6이 될때까지 실행
        while (set.size() < 6) { //0~44의 숫자를 생성(리스트의 순서도 0부터 시작하기 때문에 0~44로 설정 했습니다.)
            int random = new Random().nextInt(45); //TreeSet에 랜덤으로 만들어진 숫자 추가
            set.add(random + 1);
        }
        return set;
    }

    private void setCurrentDrw(DrwEntity entity) {
        Log.e(TAG, entity.toString());
        mBinding.winContainer.bounsAdd.setVisibility(View.GONE);
        mBinding.winContainer.bounsContainer.setVisibility(View.GONE);
        mBinding.winContainer.tvNo1.setText(String.valueOf(entity.getDrwtNo1()));
        mBinding.winContainer.tvNo2.setText(String.valueOf(entity.getDrwtNo2()));
        mBinding.winContainer.tvNo3.setText(String.valueOf(entity.getDrwtNo3()));
        mBinding.winContainer.tvNo4.setText(String.valueOf(entity.getDrwtNo4()));
        mBinding.winContainer.tvNo5.setText(String.valueOf(entity.getDrwtNo5()));
        mBinding.winContainer.tvNo6.setText(String.valueOf(entity.getDrwtNo6()));

        int targetNum = 0;
        ImageView img = mBinding.winContainer.imgShape1;
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1:
                    targetNum = entity.getDrwtNo1();
                    img = mBinding.winContainer.imgShape1;
                    break;
                case 2:
                    targetNum = entity.getDrwtNo2();
                    img = mBinding.winContainer.imgShape2;
                    break;
                case 3:
                    targetNum = entity.getDrwtNo3();
                    img = mBinding.winContainer.imgShape3;
                    break;
                case 4:
                    targetNum = entity.getDrwtNo4();
                    img = mBinding.winContainer.imgShape4;
                    break;
                case 5:
                    targetNum = entity.getDrwtNo5();
                    img = mBinding.winContainer.imgShape5;
                    break;
                case 6:
                    targetNum = entity.getDrwtNo6();
                    img = mBinding.winContainer.imgShape6;
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

    @Override
    public void onClick(View v) {
        mBinding.winContainer.getRoot().setVisibility(View.VISIBLE);
        DrwEntity entity = new DrwEntity();
        TreeSet<Integer> randomSet = null;
        switch (v.getId()) {
            case R.id.btn_random1:
                randomSet = getRandomArr();
                break;
            case R.id.btn_random2:
                randomSet = getRandomArr();
                break;
            case R.id.btn_random3:
                randomSet = getRandomArr();
                break;
            case R.id.btn_random4:
                randomSet = getRandomArr();
                break;
            case R.id.btn_save:

                break;
        }
        List<Integer> list = new ArrayList<Integer>(randomSet);
        entity.setDrwtNo1(list.get(0));
        entity.setDrwtNo2(list.get(1));
        entity.setDrwtNo3(list.get(2));
        entity.setDrwtNo4(list.get(3));
        entity.setDrwtNo5(list.get(4));
        entity.setDrwtNo6(list.get(5));
        setCurrentDrw(entity);
    }
}