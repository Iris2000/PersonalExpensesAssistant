package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
                NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mToolbar;
    TextView mUsername;
    TextView mUserEmail;
    TextView mDate;
    SharedPreferences sp;
    public String month;
    public int year;
    Dialog chooseDate;
    public ToggleButton[] toggleBtn;
    ToggleButton checkedBtn;
    String[] monthName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TransFragment()).commit();
        }

//        // get current month and year
//        Calendar calendar = Calendar.getInstance();
//        monthName = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
//                "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
//        month = monthName[calendar.get(Calendar.MONTH)];
//        year = calendar.get(Calendar.YEAR);
//        mDate = findViewById(R.id.date);
//        mDate.setText(month + " " + year);
//
        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
//
//        // NavigationView
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        View header = navigationView.getHeaderView(0);
//
//        // get username and email
//        mUsername = header.findViewById(R.id.username);
//        mUserEmail = header.findViewById(R.id.userEmail);
//        sp = getSharedPreferences("login", MODE_PRIVATE);
//        String username = sp.getString("username", "");
//        String email = sp.getString("email", "");
//        mUsername.setText(username);
//        mUserEmail.setText(email);

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

//    public void showDatePicker(View view) {
//        toggleBtn = new ToggleButton[12];
//
//        chooseDate = new Dialog(this);
//        chooseDate.setContentView(R.layout.activity_choose_date);
//
//        toggleBtn[0] = chooseDate.findViewById(R.id.toggleButton1);
//        toggleBtn[1] = chooseDate.findViewById(R.id.toggleButton2);
//        toggleBtn[2] = chooseDate.findViewById(R.id.toggleButton3);
//        toggleBtn[3] = chooseDate.findViewById(R.id.toggleButton4);
//        toggleBtn[4] = chooseDate.findViewById(R.id.toggleButton5);
//        toggleBtn[5] = chooseDate.findViewById(R.id.toggleButton6);
//        toggleBtn[6] = chooseDate.findViewById(R.id.toggleButton7);
//        toggleBtn[7] = chooseDate.findViewById(R.id.toggleButton8);
//        toggleBtn[8] = chooseDate.findViewById(R.id.toggleButton9);
//        toggleBtn[9] = chooseDate.findViewById(R.id.toggleButton10);
//        toggleBtn[10] = chooseDate.findViewById(R.id.toggleButton11);
//        toggleBtn[11] = chooseDate.findViewById(R.id.toggleButton12);
//
//        for (int i = 0 ; i < monthName.length; i++) {
//            if (month.equals(monthName[i])) {
//                toggleBtn[i].setChecked(true);
//            }
//        }
//
//        final TextView mYear = chooseDate.findViewById(R.id.yearTextView);
//        mYear.setText(Integer.toString(year));
//        final Button mLeftArrow = chooseDate.findViewById(R.id.leftBtn);
//        final Button mRightArrow = chooseDate.findViewById(R.id.rightBtn);
//        mDate = findViewById(R.id.date);
//
//        mLeftArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                year--;
//                mYear.setText(Integer.toString(year));
//                mDate.setText(month + " " + year);
//
//            }
//        });
//
//        mRightArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                year++;
//                mYear.setText(Integer.toString(year));
//                mDate.setText(month + " " + year);
//            }
//        });
//        chooseDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        chooseDate.show();
//
//
//    }
//
//    public void onMonthClicked(View view) {
//        ToggleButton checked = (ToggleButton) view;
//
//        for (int i = 0; i < toggleBtn.length; i++) {
//            if (toggleBtn[i].isChecked()) {
//                if (checkedBtn != null) {
//                    checkedBtn.setChecked(false);
//                }
//                checkedBtn = toggleBtn[i];
//            }
//        }
//        if (checked == checkedBtn) {
//            checkedBtn.setChecked(true);
//        }
//
//        month = checked.getText().toString();
//        mDate = findViewById(R.id.date);
//        mDate.setText(month + " " + year);
//    }
}
