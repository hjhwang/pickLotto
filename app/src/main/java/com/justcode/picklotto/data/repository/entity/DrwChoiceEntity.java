package com.justcode.picklotto.data.repository.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "drw_choice_table", indices = @Index(value = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"}, unique = true))

@Data
public class DrwChoiceEntity {
    @PrimaryKey(autoGenerate = true)
    private int idx;

    private int drwtNo1;
    private int drwtNo2;
    private int drwtNo3;
    private int drwtNo4;
    private int drwtNo5;
    private int drwtNo6;
}
