package com.phunware.android.phunwareproducthomework.dagger;

import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.features.add.fragment.AddZipCodeFragment;
import com.phunware.android.phunwareproducthomework.features.detail.ZipCodeDetailViewModel;
import com.phunware.android.phunwareproducthomework.features.detail.fragment.DetailFragment;
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel;
import com.phunware.android.phunwareproducthomework.features.list.fragment.ZipCodeListFragment;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ZipCodeStoreModule.class,
        WeatherSdkModule.class,
        SharedPreferencesModule.class,
        RoomModule.class})
public interface AppComponent {
    void inject(WeatherApp app);
    void inject(ZipCodeListViewModel viewModel);
    void inject(AddZipCodeFragment frag);
    void inject(ZipCodeListFragment frag);
    void inject(DetailFragment frag);
    void inject(ZipCodeDetailViewModel viewModel);

    @Component.Builder
    interface Builder {
        Builder zipCodeStoreModule(ZipCodeStoreModule module);
        Builder sharedPreferencesModule(SharedPreferencesModule module);
        Builder roomModule(RoomModule module);
        @BindsInstance
        Builder application(WeatherApp app);

        AppComponent build();
    }
}
