package com.phunware.android.phunwareproducthomework.room.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ZipCodeDao {

    @Query("SELECT * FROM zipCode")
    List<ZipCode> getAll();

    @Query("SELECT * FROM zipCode WHERE zip_code = :zipCode")
    ZipCode findByZipCode(String zipCode);

    @Insert
    void insert(ZipCode zipCode);

    @Delete
    void delete(ZipCode zipCode);
}
