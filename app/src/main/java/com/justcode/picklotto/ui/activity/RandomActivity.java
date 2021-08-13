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
import com.justcode.picklotto.ui.adapter.SelectNumberAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class RandomActivity extends BaseActivity implements View.OnClickListener {
    public static String TAG = RandomActivity.class.getSimpleName();

    private ActivityRandomBinding mBinding;

    private SelectNumberAdapter adapter = null;
    private List<Integer> mNumberArr = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_random);

        mBinding.icRandomContainer.getRoot().setVisibility(View.INVISIBLE);
        mBinding.btnReset.setOnClickListener(this);
        mBinding.btnRandom1.setOnClickListener(this);

        for(int i = 1 ; i <= 45 ; i++) {
            mNumberArr.add(i);
        }
        adapter = new SelectNumberAdapter();
        mBinding.gvSelectContainer.setAdapter(adapter);
        adapter.addItems(mNumberArr);
        /*
        mBinding.btnRandom2.setOnClickListener(this);
        mBinding.btnRandom3.setOnClickListener(this);
        mBinding.btnRandom4.setOnClickListener(this);
        */
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

    private TreeSet getSelectRandomArr(int size) {
        //중복 제거, 오름차순 정렬을 위해서 TreeSet 사용
        TreeSet<Integer> set = new TreeSet(); //TreeSet의 사이즈가 6이 될때까지 실행
        while (set.size() < 6) { //0~44의 숫자를 생성(리스트의 순서도 0부터 시작하기 때문에 0~44로 설정 했습니다.)
            int random = new Random().nextInt(size); //TreeSet에 랜덤으로 만들어진 숫자 추가
            set.add(random);
        }
        return set;
    }

    private void setCurrentDrw(DrwEntity entity) {
        Log.e(TAG, entity.toString());
        mBinding.icRandomContainer.bounsAdd.setVisibility(View.GONE);
        mBinding.icRandomContainer.bounsContainer.setVisibility(View.GONE);
        mBinding.icRandomContainer.tvNo1.setText(String.valueOf(entity.getDrwtNo1()));
        mBinding.icRandomContainer.tvNo2.setText(String.valueOf(entity.getDrwtNo2()));
        mBinding.icRandomContainer.tvNo3.setText(String.valueOf(entity.getDrwtNo3()));
        mBinding.icRandomContainer.tvNo4.setText(String.valueOf(entity.getDrwtNo4()));
        mBinding.icRandomContainer.tvNo5.setText(String.valueOf(entity.getDrwtNo5()));
        mBinding.icRandomContainer.tvNo6.setText(String.valueOf(entity.getDrwtNo6()));

        int targetNum = 0;
        ImageView img = mBinding.icRandomContainer.imgShape1;
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1:
                    targetNum = entity.getDrwtNo1();
                    img = mBinding.icRandomContainer.imgShape1;
                    break;
                case 2:
                    targetNum = entity.getDrwtNo2();
                    img = mBinding.icRandomContainer.imgShape2;
                    break;
                case 3:
                    targetNum = entity.getDrwtNo3();
                    img = mBinding.icRandomContainer.imgShape3;
                    break;
                case 4:
                    targetNum = entity.getDrwtNo4();
                    img = mBinding.icRandomContainer.imgShape4;
                    break;
                case 5:
                    targetNum = entity.getDrwtNo5();
                    img = mBinding.icRandomContainer.imgShape5;
                    break;
                case 6:
                    targetNum = entity.getDrwtNo6();
                    img = mBinding.icRandomContainer.imgShape6;
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
        DrwEntity entity = new DrwEntity();
        TreeSet<Integer> randomSet = null;
        switch (v.getId()) {
            case R.id.btn_random1:
                mBinding.icRandomContainer.getRoot().setVisibility(View.VISIBLE);
                List selectList = adapter.getSelcetList();
                randomSet = getSelectRandomArr(selectList.size());
                List list = new ArrayList<Integer>(randomSet);
                entity.setDrwtNo1((Integer) selectList.get((Integer) list.get(0)));
                entity.setDrwtNo2((Integer) selectList.get((Integer) list.get(1)));
                entity.setDrwtNo3((Integer) selectList.get((Integer) list.get(2)));
                entity.setDrwtNo4((Integer) selectList.get((Integer) list.get(3)));
                entity.setDrwtNo5((Integer) selectList.get((Integer) list.get(4)));
                entity.setDrwtNo6((Integer) selectList.get((Integer) list.get(5)));
                setCurrentDrw(entity);
                break;
            case R.id.btn_reset:

                break;
        }
    }
}