package com.phunware.android.phunwareproducthomework.dagger

import com.phunware.android.phunwareproducthomework.WeatherApp
import com.phunware.android.phunwareproducthomework.features.add.fragment.AddZipCodeFragment
import com.phunware.android.phunwareproducthomework.features.detail.ZipCodeDetailViewModel
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ZipCodeStoreModule::class)])
interface AppComponent {
    fun inject(app: WeatherApp)
    fun inject(fragment: AddZipCodeFragment)
    fun inject(model: ZipCodeListViewModel)
    fun inject(model: ZipCodeDetailViewModel)

    @Component.Builder
    interface Builder {

        fun zipCodeStoreModule(zipCodeStoreModule: ZipCodeStoreModule): Builder

        @BindsInstance
        fun application(weatherApp: WeatherApp): Builder

        fun build(): AppComponent
    }
}