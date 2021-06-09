package com.justcode.picklotto.domain.viewmodel.usecase;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.justcode.picklotto.data.repository.api.GetDrwApi;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.domain.listener.ApiJsonObjectListener;
import com.justcode.picklotto.domain.parser.DrwParser;
import com.justcode.picklotto.domain.viewmodel.usecase.listener.DrwListener;
import com.justcode.picklotto.ui.BaseActivity;

import java.util.HashSet;
import java.util.List;

public class DrwUseCase {

    public static String TAG = DrwUseCase.class.getSimpleName();

    /**
     * 회차별 정보
     */
    public static void getDrwInfo(Context context, DrwListener listener, int drwNo) {
        ApiJsonObjectListener apiListener = new ApiJsonObjectListener() {
            @Override
            public void onSuccess(int resultCode, JsonObject obj) {
                String returnValue = obj.get("returnValue").getAsString();
                Log.e(TAG, obj.toString());
                if (returnValue.equals("success")) {
                    DrwEntity entity = DrwParser.getDrwEntity(obj);
                    listener.onSuccess(entity);
                    BaseActivity.mDrwViewModel.insert(entity);
                } else {
                    listener.onFail("fail");
                }
            }

            @Override
            public void onFailed(String status) {
                Log.e(TAG, status);
            }
        };
        GetDrwApi.getDrwInfo(apiListener, drwNo);
    }

    /**
     * 빠진 데이터 찾기
     */
    public static String getHoles(List<Integer> having) {
        HashSet<Integer> holes = new HashSet<>();
        if (having.size() == 0) {
            return holes.toString();
        }
        int minSequence = having.get(having.size()-1);
        int maxSequence = having.get(0);
        for(int sequence = minSequence; sequence <= maxSequence; sequence++) {
            holes.add(new Integer(sequence));
        }
        holes.removeAll(having);
        return holes.toString();
    }

}
