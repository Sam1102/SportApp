package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shmuel.sportapp.R;

public class TrainingsExerciseExplanationActivity extends AppCompatActivity implements View.OnClickListener {

    private int exercise;
    private String linkUrl;
    private TextView textViewExplanation;
    private ImageView imageViewExplanation;
    private Button btnExplanationLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_exercise_explanation);

        initUI();
        initListeners();
    }

    private void initUI() {
        exercise = getIntent().getIntExtra("exercise", 0);

        textViewExplanation = findViewById(R.id.textViewExplanation);
        btnExplanationLink = findViewById(R.id.btnExplanationLink);
        imageViewExplanation = findViewById(R.id.imageViewExplanation);

//        if (exercise.equals("Bench press")) {
        if (exercise == R.drawable.bench_press_pro) {
            setTextsExplanations("5 sets of 8 repetitions,2 min rest", R.drawable.bench_press,
                    "https://www.youtube.com/watch?v=vcBig73ojpE");
//        } else if (exercise.equals("Incline bench press")) {
        } else if (exercise == R.drawable.incline_bench_press_pro) {
            setTextsExplanations("3 sets of 12 repetitions,1 min rest", R.drawable.incline_bench_press,
                    "https://www.youtube.com/watch?v=SrqOu55lrYU");
//        } else if (exercise.equals("Lateral elevation")) {
        } else if (exercise == R.drawable.lateral_elevaion_pro) {
            setTextsExplanations("4 sets of 10 repetitions,2 min rest", R.drawable.lateral_elevation,
                    "https://www.youtube.com/watch?v=3VcKaXpzqRo");
//        } else if (exercise.equals("Military Press")) {
        } else if (exercise == R.drawable.military_press_pro) {
            setTextsExplanations("5 sets of 8 repetitions,2 min rest", R.drawable.military_press,
                    "https://www.youtube.com/watch?v=2yjwXTZQDDI");
//        } else if (exercise.equals("Crunches")) {
        } else if (exercise == R.drawable.crunches_pro) {
            setTextsExplanations("3 sets of 50 repetitions,1 min rest", R.drawable.crunches,
                    "https://www.youtube.com/watch?v=Xyd_fa5zoEU");
//        } else if (exercise.equals("Pull up")) {
        } else if (exercise == R.drawable.pull_up_pro) {
            setTextsExplanations("5 sets of 6 repetitions,3 min rest", R.drawable.pull_up,
                    "https://www.youtube.com/watch?v=eGo4IYlbE5g");
//        } else if (exercise.equals("Rowing")) {
        } else if (exercise == R.drawable.rowing_pro) {
            setTextsExplanations("3 sets of 15 repetitions,1 min rest", R.drawable.rowing,
                    "https://www.youtube.com/watch?v=kBWAon7ItDw");
//        } else if (exercise.equals("Rowing T-bar")) {
        } else if (exercise == R.drawable.rowing_tbar_pro) {
            setTextsExplanations("4 sets of 10 repetitions,2 min rest", R.drawable.rowing_tbar,
                    "https://www.youtube.com/watch?v=j3Igk5nyZE4");
//        } else if (exercise.equals("Curl biceps")) {
        } else if (exercise == R.drawable.curl_bisceps_pro) {
            setTextsExplanations("3 sets of 20 repetitions,1 min rest", R.drawable.curl_bisceps,
                    "https://www.youtube.com/watch?v=ykJmrZ5v0Oo");
//        } else if (exercise.equals("Squat")) {
        } else if (exercise == R.drawable.squat_pro) {
            setTextsExplanations("5 sets of 10 repetitions,2 min rest", R.drawable.squat,
                    "https://www.youtube.com/watch?v=bEv6CCg2BC8");
//        } else if (exercise.equals("Lunges")) {
        } else if (exercise == R.drawable.lunges_pro) {
            setTextsExplanations("4 sets of 15 repetitions,1 min rest", R.drawable.lunges,
                    "https://www.youtube.com/watch?v=3XDriUn0udo");
//        } else if (exercise.equals("Deadlift")) {
        } else if (exercise == R.drawable.deadlift_pro) {
            setTextsExplanations("4 sets of 10 repetitions,2 min rest", R.drawable.deadlift,
                    "https://www.youtube.com/watch?v=ytGaGIn3SjE");
//        } else if (exercise.equals("Hamstring curl")) {
        } else if (exercise == R.drawable.hamstring_curl_pro) {
            setTextsExplanations("3 sets of 20 repetitions,1 min rest", R.drawable.hamstring_curl,
                    "https://www.youtube.com/watch?v=F488k67BTNo");
//        } else if (exercise.equals("Rower")) {
        } else if (exercise == R.drawable.rower_pro) {
            setTextsExplanations("15 min of rower", R.drawable.rower,
                    "https://www.youtube.com/watch?v=w2hGNM4l5so");
//        } else if (exercise.equals("Elliptical trainer")) {
        } else if (exercise == R.drawable.elliptical_trainer_pro) {
            setTextsExplanations("25 min of Elliptical trainer", R.drawable.elliptical_trainer,
                    "https://www.youtube.com/watch?v=YWfswVvOaiI");
//        } else if (exercise.equals("Running")) {
        } else if (exercise == R.drawable.running_pro) {
            setTextsExplanations("15 min of running", R.drawable.running,
                    "https://www.youtube.com/watch?v=_kGESn8ArrU");
        }
    }

    private void initListeners() {
        btnExplanationLink.setOnClickListener(this);
    }

    private void setTextsExplanations(String textViewExplanationText, int id, String linkUrlText) {
        textViewExplanation.setText(textViewExplanationText);
        imageViewExplanation.setImageResource(id);
        linkUrl = linkUrlText;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExplanationLink:
                Uri uri = Uri.parse(linkUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }

}
