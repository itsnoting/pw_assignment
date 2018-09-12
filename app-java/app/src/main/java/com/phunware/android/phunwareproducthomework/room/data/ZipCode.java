package com.phunware.android.phunwareproducthomework.room.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "zipCode")
public class ZipCode {

    public ZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="zip_code")
    private String zipCode;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}
