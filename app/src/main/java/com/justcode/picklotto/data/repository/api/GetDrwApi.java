package com.justcode.picklotto.data.repository.api;

import android.util.Log;

import com.google.gson.JsonObject;
import com.justcode.picklotto.data.constant.ResultCode;
import com.justcode.picklotto.domain.listener.ApiJsonObjectListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDrwApi {

    private static final String TAG = GetDrwApi.class.getSimpleName();

    private static int resultCode = ResultCode.RESPONSE_SUCCESS;

    public static void getDrwInfo(ApiJsonObjectListener listener, int drwNo) {
        // method=getLottoNumber
        Call<JsonObject> call = RetrofitClient.getRetrofitService().getDrw("getLottoNumber", drwNo);
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Retrofit onResponse : " + response.body().toString());
                resultCode = response.code();
                try {
                    listener.onSuccess(resultCode, response.body());
                } catch (Exception e) {
                    listener.onFailed(e.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

}
