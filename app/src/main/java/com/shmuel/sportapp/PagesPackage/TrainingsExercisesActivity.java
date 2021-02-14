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

public class TrainingsExercisesActivity extends AppCompatActivity {

    private String day;
    private RecyclerView rv;
    private List<String> arrayListTrainings = new ArrayList<>();
    private CustomAdapterTrainings adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_exercises);

        initUI();
    }

    private void initUI() {
        day = getIntent().getStringExtra("day");

        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if (day.equals("Push")) {
            arrayListTrainings = Arrays.asList("Bench press", "Incline bench press", "Lateral elevation", "Military Press", "Crunches");
        } else if (day.equals("Pull")) {
            arrayListTrainings = Arrays.asList("Pull up", "Rowing", "Rowing T-bar", "Curl biceps", "Crunches");
        } else if (day.equals("Leg")) {
            arrayListTrainings = Arrays.asList("Squat", "Lunges", "Deadlift", "Hamstring curl");
        } else if (day.equals("Cardio")) {
            arrayListTrainings = Arrays.asList("Rower", "Elliptical trainer", "Running");
        } else if (day.equals("Full body")) {
            arrayListTrainings = Arrays.asList("Squat", "Deadlift", "Bench press", "Pull up", "Crunches");
        }

        adapter = new CustomAdapterTrainings(arrayListTrainings, "Exercises", TrainingsExercisesActivity.this);
        rv.setAdapter(adapter);
    }

}
