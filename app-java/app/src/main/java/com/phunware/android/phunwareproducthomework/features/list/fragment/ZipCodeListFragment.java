package com.phunware.android.phunwareproducthomework.features.list.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel;
import com.phunware.android.phunwareproducthomework.features.list.adapter.ZipCodeAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ZipCodeListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ZipCodeAdapter viewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zip_code_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewAdapter = new ZipCodeAdapter(null);

        recyclerView = view.findViewById(R.id.zipCodesRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(viewAdapter);

        ZipCodeListViewModel model = ViewModelProviders.of(this).get(ZipCodeListViewModel.class);
        model.getZipCodes().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                viewAdapter.setItems(strings);
                viewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                ZipCodeListFragmentDirections.AddZipCode action = new ZipCodeListFragmentDirections.AddZipCode(query);

                Navigation.findNavController(getView()).navigate(action);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
