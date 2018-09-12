package com.phunware.android.phunwareproducthomework.features.add.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.features.detail.fragment.DetailFragment;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class AddZipCodeFragment extends Fragment {
    @Inject
    ZipCodeStore zipCodeStore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherApp.getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_zipcode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button addZipCodeButton = view.findViewById(R.id.addZipCodeButton);

        final String zipCode = AddZipCodeFragmentArgs.fromBundle(getArguments()).getZipCode();

        getChildFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, DetailFragment.newInstance(zipCode), "frag_detail").commit();

        addZipCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zipCodeStore.addZipCode(zipCode);

                Toast.makeText(requireContext(), zipCode + " added.", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigateUp();
            }
        });

    }
}
