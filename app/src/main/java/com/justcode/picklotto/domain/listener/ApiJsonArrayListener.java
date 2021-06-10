package com.justcode.picklotto.domain.listener;

import com.google.gson.JsonArray;

public interface ApiJsonArrayListener {
    void onSuccess(int resultCode, JsonArray array);
    void onFailed(Throwable status);
}
