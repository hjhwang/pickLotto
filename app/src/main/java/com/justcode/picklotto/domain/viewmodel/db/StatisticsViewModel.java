package com.justcode.picklotto.domain.viewmodel.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.justcode.picklotto.BaseApplication;
import com.justcode.picklotto.data.repository.dao.StatisticsDao;
import com.justcode.picklotto.data.repository.entity.StatisticsEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StatisticsViewModel extends AndroidViewModel {

    private StatisticsDao mDao;

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
        mDao = BaseApplication.mDb.statisticsDao();
    }

    public void insert(StatisticsEntity entity) {
        new insertAsyncTask(mDao).execute(entity);
    }

    public List<StatisticsEntity> getAllByBallNumber() throws ExecutionException, InterruptedException {
        return new getAllByBallNumberAsyncTask(mDao).execute().get();
    }

    public List<StatisticsEntity> getAllByCount() throws ExecutionException, InterruptedException {
        return new getAllByCountAsyncTask(mDao).execute().get();
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<StatisticsEntity, Void, Void> {
        private StatisticsDao mDao;

        public insertAsyncTask(StatisticsDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(StatisticsEntity... deviceEntities) {
            mDao.insert(deviceEntities[0]);
            return null;
        }
    }

    private static class getAllByBallNumberAsyncTask extends AsyncTask<Void, Void, List<StatisticsEntity>> {
        private StatisticsDao mDao;

        getAllByBallNumberAsyncTask(StatisticsDao dao) {
            mDao = dao;
        }

        @Override
        protected List<StatisticsEntity> doInBackground(Void... voids) {
            return mDao.getAllByBallNumber();
        }
    }

    private static class getAllByCountAsyncTask extends AsyncTask<Void, Void, List<StatisticsEntity>> {
        private StatisticsDao mDao;

        getAllByCountAsyncTask(StatisticsDao dao) {
            mDao = dao;
        }

        @Override
        protected List<StatisticsEntity> doInBackground(Void... voids) {
            return mDao.getAllByCount();
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private StatisticsDao mDao;

        deleteAllAsyncTask(StatisticsDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            return null;
        }
    }

}
