package com.phunware.android.phunwareproducthomework.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryZipCodeStore implements ZipCodeStore {
    private ArrayList<String> zipCodes = new ArrayList<>();

    @Override
    public List<String> getZipCodes() {
        return Collections.unmodifiableList(zipCodes);
    }

    @Override
    public void addZipCode(String zipCode) {
        zipCodes.add(zipCode);
    }
}
