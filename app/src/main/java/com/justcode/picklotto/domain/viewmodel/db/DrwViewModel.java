package com.justcode.picklotto.domain.viewmodel.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.justcode.picklotto.BaseApplication;
import com.justcode.picklotto.data.repository.dao.DrwDao;
import com.justcode.picklotto.data.repository.entity.DrwEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.SneakyThrows;

public class DrwViewModel extends AndroidViewModel {

    private DrwDao mDao;

    public DrwViewModel(@NonNull Application application) {
        super(application);
        mDao = BaseApplication.mDb.drwDao();
    }

    public void insert(DrwEntity entity) {
        new insertAsyncTask(mDao).execute(entity);
    }

    public List<DrwEntity> getAll() throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mDao).execute().get();
    }

    @SneakyThrows
    public int getCount() {
        return getAll() == null ? 0 : getAll().size();
    }

    public DrwEntity getDeviceByAlias(String alias) throws ExecutionException, InterruptedException {
        return new getEntityAsyncTask(mDao).execute(alias.toUpperCase()).get();
    }

    public List<Integer> getStatistics() throws ExecutionException, InterruptedException {
        return new getStatisticsAsyncTask(mDao).execute().get();
    }

    public List<Integer> getStatisticsBouns() throws ExecutionException, InterruptedException {
        return new getStatisticsBounsAsyncTask(mDao).execute().get();
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<DrwEntity, Void, Void> {
        private DrwDao mDao;

        public insertAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(DrwEntity... deviceEntities) {
            mDao.insertDrw(deviceEntities[0]);
            return null;
        }
    }

    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<DrwEntity>> {
        private DrwDao mDao;

        getAllAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected List<DrwEntity> doInBackground(Void... voids) {
            return mDao.getAll();
        }
    }

    private static class getEntityAsyncTask extends AsyncTask<String, Void, DrwEntity> {
        private DrwDao mDao;

        getEntityAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected DrwEntity doInBackground(String... strings) {
            return mDao.getDrwByDrwNo(Integer.parseInt(strings[0]));
        }
    }

    private static class getStatisticsAsyncTask extends AsyncTask<Void, Void, List> {
        private DrwDao mDao;

        getStatisticsAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected List<Integer> doInBackground(Void... voids) {
            return mDao.getStatistics();
        }
    }

    private static class getStatisticsBounsAsyncTask extends AsyncTask<Void, Void, List> {
        private DrwDao mDao;

        getStatisticsBounsAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected List<Integer> doInBackground(Void... voids) {
            return mDao.getStatisticsBouns();
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DrwDao mDao;

        deleteAllAsyncTask(DrwDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            return null;
        }
    }

}
