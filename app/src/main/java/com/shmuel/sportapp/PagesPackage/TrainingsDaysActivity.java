package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shmuel.sportapp.AdaptersPackage.CustomAdapterTrainings;
import com.shmuel.sportapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingsDaysActivity extends AppCompatActivity {

    private String level, purpose;
    private RecyclerView rv;
    private List<String> arrayListTrainings = new ArrayList<>();
    private CustomAdapterTrainings adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_days);

        initUI();
    }

    private void initUI() {
        level = getIntent().getStringExtra("level");
        purpose = getIntent().getStringExtra("purpose");

        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if (level.equals("Beginner")) {
            arrayListTrainings = Arrays.asList("Push", "Pull", "Leg");
        } else if (level.equals("Intermediate") && (purpose.equals("Making muscle") || purpose.equals("Getting in shape"))) {
            arrayListTrainings = Arrays.asList("Push", "Pull", "Leg", "Full body");
        } else if (level.equals("Intermediate") && purpose.equals("Loss weight")) {
            arrayListTrainings = Arrays.asList("Push", "Pull", "Leg", "Cardio");
        } else if (level.equals("Advanced") && (purpose.equals("Making muscle") || purpose.equals("Getting in shape"))) {
            arrayListTrainings = Arrays.asList("Leg", "Push", "Pull", "Full body", "Day 3");
        } else if (level.equals("Advanced") && purpose.equals("Loss weight")) {
            arrayListTrainings = Arrays.asList("Push", "Pull", "Leg", "Cardio", "Full body");
        }

        adapter = new CustomAdapterTrainings(arrayListTrainings, "Days", TrainingsDaysActivity.this);
        rv.setAdapter(adapter);
    }

}
