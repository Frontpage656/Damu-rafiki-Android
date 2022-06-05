package com.example.damurafiki;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.damurafiki.Fragment.AccountFrag;
import com.example.damurafiki.Fragment.HomeFrag;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainPage extends AppCompatActivity {

    Fragment fragment;
    LinearLayout to_replaced;
    BottomNavigationView bottomNavigationView;

    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        to_replaced = findViewById(R.id.to_be_replaced);
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomAppBar =findViewById(R.id.bottomAppBar);

        fragment = new HomeFrag();
        loadFragment(fragment);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:

                        fragment = new HomeFrag();
                        loadFragment(fragment);
                        break;

                    case R.id.account:
                        fragment = new AccountFrag();
                        loadFragment(fragment);
                        break;

                }

                return false;
            }
        });


    }

    private void loadFragment(Fragment fragment) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.to_be_replaced,fragment)
                    .commit();

    }

}