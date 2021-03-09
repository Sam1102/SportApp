package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shmuel.sportapp.AdaptersPackage.CustomAdapterTrainings;
import com.shmuel.sportapp.R;
import com.shmuel.sportapp.UtilsPackage.ItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingsExercisesActivity extends AppCompatActivity {

    private int day;
    private RecyclerView rv;
    private final List<Integer> arrayListTrainings = new ArrayList<>();
    private CustomAdapterTrainings adapter;
    private ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_exercises);

        initUI();
    }

    private void initUI() {
        day = getIntent().getIntExtra("day", 0);

        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        if (itemDecoration == null) {
            itemDecoration = new ItemDecoration(50);
            rv.addItemDecoration(itemDecoration);
        }

//        if (day.equals("Push")) {
        if (day == R.drawable.push) {
//            arrayListTrainings = Arrays.asList("Bench press", "Incline bench press", "Lateral elevation", "Military Press", "Crunches");
            arrayListTrainings.add(R.drawable.bench_press_pro);
            arrayListTrainings.add(R.drawable.incline_bench_press_pro);
            arrayListTrainings.add(R.drawable.lateral_elevaion_pro);
            arrayListTrainings.add(R.drawable.military_press_pro);
            arrayListTrainings.add(R.drawable.crunches_pro);
//        } else if (day.equals("Pull")) {
        } else if (day == R.drawable.pull) {
//            arrayListTrainings = Arrays.asList("Pull up", "Rowing", "Rowing T-bar", "Curl biceps", "Crunches");
            arrayListTrainings.add(R.drawable.pull_up_pro);
            arrayListTrainings.add(R.drawable.rowing_pro);
            arrayListTrainings.add(R.drawable.rowing_tbar_pro);
            arrayListTrainings.add(R.drawable.curl_bisceps_pro);
            arrayListTrainings.add(R.drawable.crunches_pro);
//        } else if (day.equals("Leg")) {
        } else if (day == R.drawable.leg) {
//            arrayListTrainings = Arrays.asList("Squat", "Lunges", "Deadlift", "Hamstring curl");
            arrayListTrainings.add(R.drawable.squat_pro);
            arrayListTrainings.add(R.drawable.lunges_pro);
            arrayListTrainings.add(R.drawable.deadlift_pro);
            arrayListTrainings.add(R.drawable.hamstring_curl_pro);
//        } else if (day.equals("Cardio")) {
        } else if (day == R.drawable.cardio) {
//            arrayListTrainings = Arrays.asList("Rower", "Elliptical trainer", "Running");
            arrayListTrainings.add(R.drawable.rower_pro);
            arrayListTrainings.add(R.drawable.elliptical_trainer_pro);
            arrayListTrainings.add(R.drawable.running_pro);
//        } else if (day.equals("Full body")) {
        } else if (day == R.drawable.fullbody) {
//            arrayListTrainings = Arrays.asList("Squat", "Deadlift", "Bench press", "Pull up", "Crunches");
            arrayListTrainings.add(R.drawable.squat_pro);
            arrayListTrainings.add(R.drawable.deadlift_pro);
            arrayListTrainings.add(R.drawable.bench_press_pro);
            arrayListTrainings.add(R.drawable.pull_up_pro);
            arrayListTrainings.add(R.drawable.crunches_pro);
        }

        adapter = new CustomAdapterTrainings(arrayListTrainings, "Exercises", TrainingsExercisesActivity.this);
        rv.setAdapter(adapter);
    }

}
