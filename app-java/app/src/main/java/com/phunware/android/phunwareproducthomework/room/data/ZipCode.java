package com.phunware.android.phunwareproducthomework.room.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "zipCode")
public class ZipCode {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="zid")
    private int id;

    @ColumnInfo(name="zip_code")
    private String zipCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }
}
