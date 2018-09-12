package com.phunware.android.phunwareproducthomework.features.list.fragment;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel;
import com.phunware.android.phunwareproducthomework.features.list.adapter.ZipCodeAdapter;
import com.phunware.android.phunwareproducthomework.model.UnitEnum;
import com.phunware.android.phunwareproducthomework.room.data.ZipCode;
import com.phunware.android.phunwareproducthomework.storage.SettingsSharedPreferences;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.AppCompatEditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ZipCodeListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ZipCodeAdapter viewAdapter;

    @Inject
     SettingsSharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        WeatherApp.getApplicationComponent().inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zip_code_list, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fahrenheit) {
            Log.v("UNIT", "switched to fahrenheit");
            item.setChecked(true);
            sharedPreferences.putData("unit", UnitEnum.fahrenheit.getUnitSystem());
            Toast.makeText(getContext(), R.string.switch_f, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.celsius) {
            Log.v("UNIT", "switched to celsius");
            item.setChecked(true);
            sharedPreferences.putData("unit", UnitEnum.celsius.getUnitSystem());
            Toast.makeText(getContext(), R.string.switch_c, Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewAdapter = new ZipCodeAdapter(null);

        recyclerView = view.findViewById(R.id.zipCodesRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(viewAdapter);

        ZipCodeListViewModel model = ViewModelProviders.of(this).get(ZipCodeListViewModel.class);
        model.getZipCodes().observe(this, new Observer<List<ZipCode>>() {
            @Override
            public void onChanged(List<ZipCode> strings) {
                viewAdapter.setItems(strings);
                viewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);

        Menu options = menu.findItem(R.id.options).getSubMenu();
        MenuItem fahrenheit = options.findItem(R.id.fahrenheit);
        MenuItem celsius = options.findItem(R.id.celsius);
        if (sharedPreferences.getData("unit").equals(UnitEnum.fahrenheit.getUnitSystem())) {
            fahrenheit.setChecked(true);
        } else if (sharedPreferences.getData("unit").equals(UnitEnum.celsius.getUnitSystem())) {
            celsius.setChecked(true);
        }

        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        // Add filters for the input text to validate zip codes.
        TextView searchText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);

        //
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (query.length() == 5) {
                    ZipCodeListFragmentDirections.AddZipCode action = new ZipCodeListFragmentDirections.AddZipCode(query);
                    Navigation.findNavController(getView()).navigate(action);
                    return true;
                } else {
                    Toast.makeText(getActivity(), R.string.invalid_zip_length, Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
