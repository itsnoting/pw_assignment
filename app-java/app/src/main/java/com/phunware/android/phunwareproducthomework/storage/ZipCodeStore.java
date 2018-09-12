package com.phunware.android.phunwareproducthomework.storage;

import java.util.List;

public interface ZipCodeStore {
    List<String> getZipCodes();
    void addZipCode(String zipCode);
}
