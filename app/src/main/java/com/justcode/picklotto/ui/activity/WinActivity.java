package com.justcode.picklotto.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.justcode.picklotto.R;
import com.justcode.picklotto.databinding.ActivityWinBinding;

public class WinActivity extends AppCompatActivity {
    public static String TAG = WinActivity.class.getSimpleName();

    private ActivityWinBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_win);

        WebSettings mws = mBinding.wbWin.getSettings();//Mobile Web Setting
        mws.setJavaScriptEnabled(true);//자바스크립트 허용
        // mws.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정

        mBinding.wbWin.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mBinding.wbWin.loadUrl(getIntent().getStringExtra("winAddress"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // QR코드 스캔한 결과
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // result.getFormatName() : 바코드 종류
        // result.getContents() : 바코드 값
        // mTextMessage.setText( result.getContents() );
        if (result == null) return;
        String address = result.getContents();
        if (!address.startsWith("http://")) {
            address = "http://" + address;
        }
        Log.e(TAG, result.getContents());
        mBinding.wbWin.loadUrl(address);
    }
}