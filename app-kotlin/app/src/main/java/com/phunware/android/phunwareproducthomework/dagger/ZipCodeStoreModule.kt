package com.phunware.android.phunwareproducthomework.dagger

import com.phunware.android.phunwareproducthomework.storage.InMemoryZipCodeStore
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ZipCodeStoreModule {
  @Provides
  @Singleton
  fun provideZipCodeStore(): ZipCodeStore = InMemoryZipCodeStore()
}
