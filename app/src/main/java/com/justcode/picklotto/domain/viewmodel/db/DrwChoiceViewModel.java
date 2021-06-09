package com.justcode.picklotto.domain.viewmodel.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.justcode.picklotto.BaseApplication;
import com.justcode.picklotto.data.repository.dao.DrwChoiceDao;
import com.justcode.picklotto.data.repository.entity.DrwChoiceEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.SneakyThrows;

public class DrwChoiceViewModel extends AndroidViewModel {

    private DrwChoiceDao mDao;

    public DrwChoiceViewModel(@NonNull Application application) {
        super(application);
        mDao = BaseApplication.mDb.drwChoiceDao();
    }

    public void insert(DrwChoiceEntity entity) {
        new insertAsyncTask(mDao).execute(entity);
    }

    public List<DrwChoiceEntity> getAll() throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mDao).execute().get();
    }

    @SneakyThrows
    public int getCount() {
        return getAll() == null ? 0 : getAll().size();
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<DrwChoiceEntity, Void, Void> {
        private DrwChoiceDao mDao;

        public insertAsyncTask(DrwChoiceDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(DrwChoiceEntity... deviceChoiceEntities) {
            mDao.insertDrw(deviceChoiceEntities[0]);
            return null;
        }
    }

    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<DrwChoiceEntity>> {
        private DrwChoiceDao mDao;

        getAllAsyncTask(DrwChoiceDao dao) {
            mDao = dao;
        }

        @Override
        protected List<DrwChoiceEntity> doInBackground(Void... voids) {
            return mDao.getAll();
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DrwChoiceDao mDao;

        deleteAllAsyncTask(DrwChoiceDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            return null;
        }
    }

}
