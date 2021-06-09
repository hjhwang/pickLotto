package com.justcode.picklotto.data.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.justcode.picklotto.data.repository.entity.DrwChoiceEntity;
import com.justcode.picklotto.data.repository.entity.DrwEntity;

import java.util.List;

@Dao
public interface DrwChoiceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDrw(DrwChoiceEntity entity);

    @Query("SELECT * FROM DRW_CHOICE_TABLE")
    List<DrwChoiceEntity> getAll();

    @Query("DELETE FROM DRW_CHOICE_TABLE")
    void deleteAll();

}
