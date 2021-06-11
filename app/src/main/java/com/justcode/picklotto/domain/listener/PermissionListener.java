package com.justcode.picklotto.domain.listener;

public interface PermissionListener {
    void onPermissionGranted();
    void onPermissionDenied();
}
