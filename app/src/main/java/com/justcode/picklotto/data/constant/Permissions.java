package com.justcode.picklotto.data.constant;

import android.Manifest;

public class Permissions {
    /**
     * Q버전 이상
     */
    public static String[] Q_PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA
    };
    /**
     * M버전 이상
     */
    public static String[] M_PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA
    };
}
