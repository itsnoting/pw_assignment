package com.phunware.android.phunwareproducthomework.features.detail.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.features.detail.ZipCodeDetailViewModel;
import com.phunware.android.phunwareproducthomework.storage.SettingsSharedPreferences;
import com.phunware.android.weathersdk.models.Weather;

import java.util.Date;
import java.util.Set;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailFragment extends Fragment {

    @Inject
    SettingsSharedPreferences sharedPreferences;

    public static DetailFragment newInstance(String zipCode) {
        DetailFragment frag = new DetailFragment();
        frag.setArguments(new DetailFragmentArgs.Builder(zipCode).build().toBundle());
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherApp.getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Group loadingGroup = view.findViewById(R.id.loadingGroup);
        final Group weatherGroup = view.findViewById(R.id.weatherGroup);
        final TextView zipCodeTextView = view.findViewById(R.id.zipCodeTextView);
        final TextView currentTempTextView = view.findViewById(R.id.currentTempTextView);
        final TextView highTempTextView = view.findViewById(R.id.highTempTextView);
        final TextView lowTempTextView = view.findViewById(R.id.lowTempTextView);
        final TextView sunriseTextView = view.findViewById(R.id.sunriseTextView);
        final TextView sunsetTextView = view.findViewById(R.id.sunsetTextView);

        loadingGroup.setVisibility(View.VISIBLE);
        weatherGroup.setVisibility(View.GONE);

        final String zipCode = DetailFragmentArgs.fromBundle(getArguments()).getZipCode();
//        final String unit = DetailFragmentArgs.fromBundle(getArguments()).getUnit();

        ZipCodeDetailViewModel model = ViewModelProviders.of(this).get(ZipCodeDetailViewModel.class);
        model.getWeather(zipCode, sharedPreferences.getData("unit")).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                if (weather != null) {
                    zipCodeTextView.setText(getString(R.string.detail_title, weather.getName(), zipCode));
                    currentTempTextView.setText(String.valueOf(weather.getTemperatures().getTemp()));
                    highTempTextView.setText(String.valueOf(weather.getTemperatures().getTempMax()));
                    lowTempTextView.setText(String.valueOf(weather.getTemperatures().getTempMin()));

                    sunriseTextView.setText(new Date(weather.getLocationInfo().getSunrise() * 1000 /*Converting epoch time to milliseconds*/).toString());
                    sunsetTextView.setText(new Date(weather.getLocationInfo().getSunset() * 1000 /*Converting epoch time to milliseconds*/).toString());
                    weatherGroup.setVisibility(View.VISIBLE);
                    loadingGroup.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), R.string.timeout_message, Toast.LENGTH_SHORT).show();
                    weatherGroup.setVisibility(View.GONE);
                    loadingGroup.setVisibility(View.GONE);
                }
            }
        });
    }
}
