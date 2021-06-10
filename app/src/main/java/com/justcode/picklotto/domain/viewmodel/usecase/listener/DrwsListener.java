package com.justcode.picklotto.domain.viewmodel.usecase.listener;

import com.google.gson.JsonArray;

public interface DrwsListener {
    void onLoadComplete(JsonArray array);
    void onCancelComplete(JsonArray array);
    void onSetComplete();
}
