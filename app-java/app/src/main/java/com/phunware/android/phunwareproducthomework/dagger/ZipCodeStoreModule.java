package com.phunware.android.phunwareproducthomework.dagger;

import com.phunware.android.phunwareproducthomework.storage.InMemoryZipCodeStore;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ZipCodeStoreModule {
    @Provides
    @Singleton
    ZipCodeStore provideZipCodeStore() {
        return new InMemoryZipCodeStore();
    }
}
