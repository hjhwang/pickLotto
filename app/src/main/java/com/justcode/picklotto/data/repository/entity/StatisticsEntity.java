package com.justcode.picklotto.data.repository.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "statistics_table", indices = @Index(value = {"ballNumber"}, unique = true))

@Data
public class StatisticsEntity {
    @PrimaryKey(autoGenerate = true)
    private int idx;

    private int ballNumber;
    private int count;
}
