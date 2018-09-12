package com.phunware.android.phunwareproducthomework.features.list;

import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZipCodeListViewModel extends ViewModel {
    @Inject
    ZipCodeStore zipCodeStore;

    public ZipCodeListViewModel() {
        WeatherApp.getApplicationComponent().inject(this);
    }

    private MutableLiveData<List<String>> zipCodes = null;

    public LiveData<List<String>> getZipCodes() {
        if (zipCodes == null) {
            zipCodes = new MutableLiveData<>();
        }

        loadZipCodes();

        return zipCodes;
    }

    private void loadZipCodes() {
        zipCodes.setValue(zipCodeStore.getZipCodes());
    }
}
