package com.phunware.android.phunwareproducthomework.room.repository;

import com.phunware.android.phunwareproducthomework.room.data.ZipCode;

import java.util.List;

public interface ZipCodeRepository {

    ZipCode findByZipCode(String zipCode);

    List<ZipCode> getAll();

    long insert(ZipCode zipCode);

    int delete(ZipCode zipCode);
}
