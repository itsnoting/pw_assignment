package com.phunware.android.phunwareproducthomework.features.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.features.detail.ZipCodeDetailViewModel;
import com.phunware.android.weathersdk.models.Weather;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailFragment extends Fragment {
    public static DetailFragment newInstance(String zipCode) {
        DetailFragment frag = new DetailFragment();
        frag.setArguments(new DetailFragmentArgs.Builder(zipCode).build().toBundle());
        return frag;
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
        final TextView sunriseTextView = view.findViewById(R.id.sunriseTextView);
        final TextView sunsetTextView = view.findViewById(R.id.sunsetTextView);

        loadingGroup.setVisibility(View.VISIBLE);
        weatherGroup.setVisibility(View.GONE);

        final String zipCode = DetailFragmentArgs.fromBundle(getArguments()).getZipCode();

        ZipCodeDetailViewModel model = ViewModelProviders.of(this).get(ZipCodeDetailViewModel.class);
        model.getWeather(zipCode).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                zipCodeTextView.setText(getString(R.string.detail_title, weather.getName(), zipCode));
                currentTempTextView.setText(String.valueOf(weather.getTemperatures().getTemp()));
                highTempTextView.setText(String.valueOf(weather.getTemperatures().getTempMax()));

                sunriseTextView.setText(new Date(weather.getLocationInfo().getSunrise()).toString());
                sunsetTextView.setText(new Date(weather.getLocationInfo().getSunset()).toString());

                loadingGroup.setVisibility(View.GONE);
                weatherGroup.setVisibility(View.VISIBLE);

            }
        });
    }
}
