package com.justcode.picklotto.domain.viewmodel.usecase.listener;

import com.google.gson.JsonObject;
import com.justcode.picklotto.data.repository.entity.DrwEntity;

public interface DrwListener {
    void onSuccess(DrwEntity entity);
    void onFail(String msg);
}
