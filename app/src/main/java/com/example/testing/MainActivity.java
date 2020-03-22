package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements
                NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mToolbar;
    TextView mUsername;
    TextView mUserEmail;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TransFragment()).commit();
        }

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        // NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        // get username and email
        mUsername = header.findViewById(R.id.username);
        mUserEmail = header.findViewById(R.id.userEmail);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        String username = sp.getString("username", "");
        String email = sp.getString("email", "");
        mUsername.setText(username);
        mUserEmail.setText(email);

        // bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_trans:
                        Toast.makeText(MainActivity.this, "Trans", Toast.LENGTH_SHORT).show();
                        selectedFragment = new TransFragment();
                        break;
                    case R.id.action_budget:
                        Toast.makeText(MainActivity.this, "Budget", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_cat:
                        Toast.makeText(MainActivity.this, "Cat", Toast.LENGTH_SHORT).show();
//                        selectedFragment = new CatFragment();
                        break;
                    case R.id.action_chart:
                        Toast.makeText(MainActivity.this, "Chart", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        // Do nothing
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                sp.edit().clear().apply();
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }
}
