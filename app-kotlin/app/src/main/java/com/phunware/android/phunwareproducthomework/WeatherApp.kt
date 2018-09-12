package com.phunware.android.phunwareproducthomework

import android.app.Application
import com.phunware.android.phunwareproducthomework.dagger.AppComponent
import com.phunware.android.phunwareproducthomework.dagger.DaggerAppComponent
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore
import javax.inject.Inject

class WeatherApp : Application() {

    companion object {
        private lateinit var applicationComponent: AppComponent

        fun getApplicationComponent(): AppComponent {
            return applicationComponent
        }
    }

    @Inject
    lateinit var zipCodeStore: ZipCodeStore

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder().application(this).build()
        applicationComponent.inject(this)

        zipCodeStore.addZipCode("92029")
        zipCodeStore.addZipCode("92128")
        zipCodeStore.addZipCode("03872")
    }
}