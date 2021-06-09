package com.justcode.picklotto.domain.listener;

import com.google.gson.JsonObject;

public interface ApiJsonObjectListener {
    void onSuccess(int resultCode, JsonObject obj);
    void onFailed(String status);
}
