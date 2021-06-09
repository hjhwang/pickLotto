package com.justcode.picklotto.data.constant;

public class ResultCode {
    /**
     * 성공
     */
    public static final int RESPONSE_SUCCESS = 200;
    /**
     * 바코드 존재여부
     */
    public static final int RESPONSE_NOT_FOUND = 404;
    /**
     * 디바이스 중복 로그인 방지
     */
    public static final int RESPONSE_DUPLICATE_DEVICE = 400;
    /**
     * 인수인계 오류
     */
    public static final int RESPONSE_NOT_TRIP = 400;
    /**
     * 인수인계 여부
     */
    public static final int RESPONSE_TAKEOVER = 204;
}
