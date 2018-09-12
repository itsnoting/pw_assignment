package com.phunware.android.phunwareproducthomework.dagger;

import android.app.Application;

import com.phunware.android.phunwareproducthomework.room.data.ZipCodeDao;
import com.phunware.android.phunwareproducthomework.room.repository.AppDatabase;
import com.phunware.android.phunwareproducthomework.room.repository.ZipCodeDataSource;
import com.phunware.android.phunwareproducthomework.room.repository.ZipCodeRepository;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase database;

    public RoomModule(Application app) {
        database = Room.databaseBuilder(app, AppDatabase.class, "db").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    ZipCodeDao providesZipCodeDao(AppDatabase database) {
        return database.getZipCodeDao();
    }

    @Singleton
    @Provides
    ZipCodeRepository zipCodeRepository(ZipCodeDao zipCodeDao) {
        return new ZipCodeDataSource(zipCodeDao);
    }

}
