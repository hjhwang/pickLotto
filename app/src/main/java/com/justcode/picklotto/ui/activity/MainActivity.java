package com.justcode.picklotto.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.justcode.picklotto.R;
import com.justcode.picklotto.data.constant.ActivityRequestCode;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.databinding.ActivityMainBinding;
import com.justcode.picklotto.databinding.LayoutLinearWinBinding;
import com.justcode.picklotto.domain.viewmodel.usecase.DrwUseCase;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwListener;
import com.justcode.picklotto.ui.BaseActivity;

import java.math.BigDecimal;
import java.util.List;

import lombok.SneakyThrows;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static String TAG = MainActivity.class.getSimpleName();
    // https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=964 // api 적용
    // 2021-05-22 946회
    // db 최신 +1 fail일 경우 예외처리
    // 최초 실행시 1회차 부터 db저장
    // git access token : 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
    /*
    // imageView.setColorFilter(getResources().getColor(R.color.color_white));
    */
    // 로또 공이미지 1~45번 받아놓기
    // bottom navigation : home(현재 회차 정보), QR, average(통계 그래프), select(random) : 상단(30개 통계 그래프)

    // TODO : Admob
    // 1. ca-app-pub-2744046875475466~8695433737
    // 2. ca-app-pub-2744046875475466/2976203191

    private ActivityMainBinding mBinding;
    private LayoutLinearWinBinding mWinBinding;

    private int currentDrwNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 마지막 데이터로 날짜로 최신 추첨회차 받아오기
        mBinding.btnWin.setOnClickListener(this);
        mBinding.btnDrw.setOnClickListener(this);
        mBinding.btnStatistics.setOnClickListener(this);
        mBinding.btnRandom.setOnClickListener(this);
        mBinding.icWinContainer.btnLeft.setOnClickListener(this);
        mBinding.icWinContainer.btnRight.setOnClickListener(this);
        mBinding.icWinContainer.leftArrow.setVisibility(View.VISIBLE);
        mBinding.icWinContainer.rightArrow.setVisibility(View.VISIBLE);
        currentDrwNo = finalDrwNo;
        DrwUseCase.getDrwInfo(this, mDrwListener, currentDrwNo);

        /*
        add admob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mBinding.adView.loadAd(adRequest);
        */
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // QR코드 스캔한 결과
        if (data == null) return;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String address = result.getContents();
        if (!address.startsWith("http://")) {
            address = "http://" + address;
        }
        Intent intent = new Intent(this, WinActivity.class);
        intent.putExtra("winAddress", address);
        mContext.startActivityForResult(intent, ActivityRequestCode.ZXING_CODE);
    }

    DrwListener mDrwListener = new DrwListener() {
        @Override
        public void onSuccess(DrwEntity entity) {
            setCurrentDrw(entity);
        }

        @Override
        public void onFail(String msg) {
            currentDrwNo--;
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Lotto").setMessage("다음 회차가 없습니다.");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.show();
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_win:
                /*new Handler().postDelayed(() -> {
                    IntentIntegrator integrator = new IntentIntegrator(this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                    integrator.setPrompt("QR코드를 사각형 안에 비춰주세요.");
                    integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setBeepEnabled(false);
                    integrator.setOrientationLocked(false);
                    integrator.setBarcodeImageEnabled(true);
                    integrator.initiateScan();
                }, 2000);*/
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setBeepEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.btn_drw:
                intent = new Intent(this, DrwActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_statistics:
                intent = new Intent(this, StatisticsActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_random:
                intent = new Intent(this, RandomActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_left:
                if (currentDrwNo != 1) {
                    currentDrwNo--;
                    DrwUseCase.getDrwInfo(this, mDrwListener, currentDrwNo);
                }
                break;
            case R.id.btn_right:
                currentDrwNo++;
                DrwUseCase.getDrwInfo(this, mDrwListener, currentDrwNo);
                break;
        }
    }

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

        mBinding.tvAmntData.setText(finalTotal + "억원 " + "(" + entity.getFirstPrzwnerCo() + "명/" + finalWin + "억)");

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