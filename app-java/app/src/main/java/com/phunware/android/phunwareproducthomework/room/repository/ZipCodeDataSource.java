package com.phunware.android.phunwareproducthomework.room.repository;

import com.phunware.android.phunwareproducthomework.room.data.ZipCode;
import com.phunware.android.phunwareproducthomework.room.data.ZipCodeDao;

import java.util.List;

import javax.inject.Inject;

public class ZipCodeDataSource implements ZipCodeRepository {

    private ZipCodeDao zipCodeDao;

    @Inject
    public ZipCodeDataSource(ZipCodeDao zipCodeDao) {
        this.zipCodeDao = zipCodeDao;
    }

    @Override
    public ZipCode findByZipCode(String zipCode) {
        return this.zipCodeDao.findByZipCode(zipCode);
    }

    @Override
    public List<ZipCode> getAll() {
        return this.zipCodeDao.getAll();
    }

    @Override
    public long insert(ZipCode zipCode) {
        this.zipCodeDao.insert(zipCode);
        return 0;
    }

    @Override
    public int delete(ZipCode zipCode) {
        this.zipCodeDao.delete(zipCode);
        return 0;
    }
}
