package com.justcode.picklotto.data.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.justcode.picklotto.data.repository.entity.StatisticsEntity;

import java.util.List;

@Dao
public interface StatisticsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StatisticsEntity entity);

    @Query("SELECT * FROM STATISTICS_TABLE ORDER BY ballNumber ASC")
    List<StatisticsEntity> getAllByBallNumber();

    @Query("SELECT * FROM STATISTICS_TABLE ORDER BY count DESC")
    List<StatisticsEntity> getAllByCount();

    @Query("DELETE FROM STATISTICS_TABLE")
    void deleteAll();
}
