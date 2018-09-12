package com.phunware.android.phunwareproducthomework.room.repository;

import com.phunware.android.phunwareproducthomework.room.data.ZipCode;
import com.phunware.android.phunwareproducthomework.room.data.ZipCodeDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ZipCode.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract ZipCodeDao getZipCodeDao();
}
