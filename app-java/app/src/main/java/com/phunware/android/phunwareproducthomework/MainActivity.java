package com.phunware.android.phunwareproducthomework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationUI.setupActionBarWithNavController(this, findNavController(this, R.id.my_nav_host_fragment));
    }

    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }
}
