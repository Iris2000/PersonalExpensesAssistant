package com.example.testing;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransFragment extends Fragment implements ToggleButton.OnClickListener {

    View transView;
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
    ToggleButton checkedBtn = null;
    String[] monthName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        transView = inflater.inflate(R.layout.fragment_trans, container, false);

//        mToolbar = transView.findViewById(R.id.tool_bar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mDrawerLayout = transView.findViewById(R.id.drawer);
//        mToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();

        // get current month and year
        Calendar calendar = Calendar.getInstance();
        monthName = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        month = monthName[calendar.get(Calendar.MONTH)];
        year = calendar.get(Calendar.YEAR);
        mDate = transView.findViewById(R.id.date);
        mDate.setText(month + " " + year);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v);
            }
        });

        return transView;
    }

    public void showDatePicker(View view) {
        toggleBtn = new ToggleButton[12];

        chooseDate = new Dialog(getActivity());
        chooseDate.setContentView(R.layout.activity_choose_date);

        toggleBtn[0] = chooseDate.findViewById(R.id.toggleButton1);
        toggleBtn[1] = chooseDate.findViewById(R.id.toggleButton2);
        toggleBtn[2] = chooseDate.findViewById(R.id.toggleButton3);
        toggleBtn[3] = chooseDate.findViewById(R.id.toggleButton4);
        toggleBtn[4] = chooseDate.findViewById(R.id.toggleButton5);
        toggleBtn[5] = chooseDate.findViewById(R.id.toggleButton6);
        toggleBtn[6] = chooseDate.findViewById(R.id.toggleButton7);
        toggleBtn[7] = chooseDate.findViewById(R.id.toggleButton8);
        toggleBtn[8] = chooseDate.findViewById(R.id.toggleButton9);
        toggleBtn[9] = chooseDate.findViewById(R.id.toggleButton10);
        toggleBtn[10] = chooseDate.findViewById(R.id.toggleButton11);
        toggleBtn[11] = chooseDate.findViewById(R.id.toggleButton12);

        for (int i = 0; i < toggleBtn.length; i++) {
            toggleBtn[i].setOnClickListener(this);
        }

        for (int i = 0 ; i < monthName.length; i++) {
            if (month.equals(monthName[i])) {
                toggleBtn[i].setChecked(true);
            }
        }

        final TextView mYear = chooseDate.findViewById(R.id.yearTextView);
        mYear.setText(Integer.toString(year));
        final Button mLeftArrow = chooseDate.findViewById(R.id.leftBtn);
        final Button mRightArrow = chooseDate.findViewById(R.id.rightBtn);
        mDate = transView.findViewById(R.id.date);

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year--;
                mYear.setText(Integer.toString(year));
                mDate.setText(month + " " + year);

            }
        });

        mRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year++;
                mYear.setText(Integer.toString(year));
                mDate.setText(month + " " + year);
            }
        });
        chooseDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chooseDate.show();
    }

    @Override
    public void onClick(View view) {
        ToggleButton checked = (ToggleButton) view;

        for (int i = 0; i < toggleBtn.length; i++) {
            if (toggleBtn[i].isChecked()) {
                if (checkedBtn != null) {
                    checkedBtn.setChecked(false);
                }
                checkedBtn = toggleBtn[i];
            }
        }
        if (checked == checkedBtn) {
            checkedBtn.setChecked(true);
        }

        month = checked.getText().toString();
        mDate = transView.findViewById(R.id.date);
        mDate.setText(month + " " + year);
    }
}