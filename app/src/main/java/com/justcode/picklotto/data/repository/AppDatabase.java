package com.justcode.picklotto.data.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.justcode.picklotto.data.repository.dao.DrwChoiceDao;
import com.justcode.picklotto.data.repository.dao.DrwDao;
import com.justcode.picklotto.data.repository.dao.StatisticsDao;
import com.justcode.picklotto.data.repository.entity.DrwChoiceEntity;
import com.justcode.picklotto.data.repository.entity.DrwEntity;
import com.justcode.picklotto.data.repository.entity.StatisticsEntity;

@Database(entities = {DrwEntity.class, DrwChoiceEntity.class, StatisticsEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static String dbName = "pick_lotto.db";

    private static AppDatabase INSTANCE;
    public abstract DrwDao drwDao();
    public abstract DrwChoiceDao drwChoiceDao();
    public abstract StatisticsDao statisticsDao();

    static final Migration MIGRATION_1_1 = new Migration(1, 1) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static AppDatabase getDatabase(Context context) {
        synchronized (AppDatabase.class) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName)
                        .addMigrations(MIGRATION_1_1).fallbackToDestructiveMigration().build();
            }
            return INSTANCE;
        }
    }
}
