package com.phunware.android.phunwareproducthomework.features.list;

import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.room.data.ZipCode;
import com.phunware.android.phunwareproducthomework.room.repository.AppDatabase;
import com.phunware.android.phunwareproducthomework.room.repository.ZipCodeRepository;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZipCodeListViewModel extends ViewModel {
    @Inject
    ZipCodeStore zipCodeStore;

    @Inject
    ZipCodeRepository zipCodeRepository;

    public ZipCodeListViewModel() {
        WeatherApp.getApplicationComponent().inject(this);
    }

    private MutableLiveData<List<ZipCode>> zipCodes = null;

    public LiveData<List<ZipCode>> getZipCodes() {
        if (zipCodes == null) {
            zipCodes = new MutableLiveData<>();
        }

        loadZipCodes();

        return zipCodes;
    }

    private void loadZipCodes() {
        zipCodes.setValue(zipCodeRepository.getAll());
    }
}
