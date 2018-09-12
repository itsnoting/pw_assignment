package com.phunware.android.phunwareproducthomework.dagger;

import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.features.add.fragment.AddZipCodeFragment;
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ZipCodeStoreModule.class})
public interface AppComponent {
    void inject(WeatherApp app);
    void inject(ZipCodeListViewModel viewModel);
    void inject(AddZipCodeFragment frag);

    @Component.Builder
    interface Builder {
        Builder zipCodeStoreModule(ZipCodeStoreModule module);

        @BindsInstance
        Builder application(WeatherApp app);

        AppComponent build();
    }
}
