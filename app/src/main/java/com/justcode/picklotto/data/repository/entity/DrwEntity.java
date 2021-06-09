package com.justcode.picklotto.data.repository.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "drw_table", indices = @Index(value = {"drwNo", "drwNoDate"}, unique = true))

@Data
public class DrwEntity {
    /*
    {
    "drwNo":964,
    "returnValue":"success",
    "drwNoDate":"2021-05-22",
    "totSellamnt":95549895000,
    "firstAccumamnt":23458610630,
    "firstWinamnt":2345861063,
    "firstPrzwnerCo":10,
    "drwtNo1":6,
    "drwtNo2":21,
    "drwtNo3":36,
    "drwtNo4":38,
    "drwtNo5":39,
    "drwtNo6":43,
    "bnusNo":30
    }
    */
    @PrimaryKey(autoGenerate = true)
    private int idx;

    private int drwNo;// 회차
    private String returnValue;// success, fail
    private String drwNoDate;// 추첨날짜
    private long totSellamnt;// 총판매금
    private long firstAccumamnt;// 총상금
    private long firstWinamnt;// 1등 상금
    private int firstPrzwnerCo;// 1등 당첨 인원
    // 당첨 번호 순서
    private int drwtNo1;
    private int drwtNo2;
    private int drwtNo3;
    private int drwtNo4;
    private int drwtNo5;
    private int drwtNo6;
    private int bnusNo;
}
