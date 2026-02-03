package com.vincent.java.ui.component;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.vincent.java.R;
import com.vincent.java.fragments.ContactFragment;
import com.vincent.java.fragments.DiscoveryFragment;
import com.vincent.java.fragments.HomeFragment;
import com.vincent.java.fragments.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {

    List<Fragment> list;


    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TabsView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigation = findViewById(R.id.bottomNav);

        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new ContactFragment());
        list.add(new DiscoveryFragment());
        list.add(new MeFragment());
        showFragment(list.get(0));

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId() == R.id.menu_contact) {
                    showFragment(list.get(1));
                } else if(item.getItemId() == R.id.menu_discovery) {
                    showFragment(list.get(2));
                } else if(item.getItemId() == R.id.menu_me) {
                    showFragment(list.get(3));
                } else {
                    showFragment(list.get(0));
                }
                return true;
            }
        });
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.TabsView, fragment);
        ft.commit();
    }
}