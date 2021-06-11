package com.justcode.picklotto.data.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.justcode.picklotto.data.repository.entity.DrwEntity;

import java.util.List;

@Dao
public interface DrwDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDrw(DrwEntity entity);

    @Query("SELECT * FROM DRW_TABLE")
    List<DrwEntity> getAll();

    @Query("SELECT * FROM DRW_TABLE LIMIT 30")
    List<DrwEntity> getLimitFifteen();

    @Query("SELECT * FROM DRW_TABLE LIMIT 60")
    List<DrwEntity> getLimitThirty();

    @Query("SELECT * FROM DRW_TABLE LIMIT 90")
    List<DrwEntity> getLimitFifty();

    @Query("SELECT * FROM DRW_TABLE WHERE drwNo=:drwNo")
    DrwEntity getDrwByDrwNo(int drwNo);

    @Query("SELECT * FROM DRW_TABLE ORDER BY drwNo DESC LIMIT 1")
    DrwEntity getLastDrwNo();

    @Query("DELETE FROM DRW_TABLE")
    void deleteAll();

    @Query("SELECT SUM(cnt) AS cnt FROM (" +
            "SELECT drwtNo1 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo1 UNION ALL " +
            "SELECT drwtNo2 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo2 UNION ALL " +
            "SELECT drwtNo3 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo3 UNION ALL " +
            "SELECT drwtNo4 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo4 UNION ALL " +
            "SELECT drwtNo5 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo5 UNION ALL " +
            "SELECT drwtNo6 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo6" +
            ") GROUP BY lottoNo ORDER BY lottoNo ASC")
    List<Integer> getStatistics();

    @Query("SELECT SUM(cnt) AS cnt FROM (" +
            "SELECT drwtNo1 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo1 UNION ALL " +
            "SELECT drwtNo2 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo2 UNION ALL " +
            "SELECT drwtNo3 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo3 UNION ALL " +
            "SELECT drwtNo4 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo4 UNION ALL " +
            "SELECT drwtNo5 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo5 UNION ALL " +
            "SELECT drwtNo6 lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY drwtNo6 UNION ALL " +
            "SELECT bnusNo lottoNo, COUNT(*) CNT FROM DRW_TABLE GROUP BY bnusNo" +
            ") GROUP BY lottoNo ORDER BY lottoNo ASC")
    List<Integer> getStatisticsBouns();
}
