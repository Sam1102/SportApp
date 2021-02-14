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

    private String exercise, linkUrl;
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
        exercise = getIntent().getStringExtra("exercise");

        textViewExplanation = findViewById(R.id.textViewExplanation);
        btnExplanationLink = findViewById(R.id.btnExplanationLink);
        imageViewExplanation = findViewById(R.id.imageViewExplanation);

        if (exercise.equals("Bench press")) {
            setTextsExplanations("5 sets of 8 repetitions,2 min rest", R.drawable.bench_press,
                    "https://www.youtube.com/watch?v=sDirS1jQuCc");
        } else if (exercise.equals("Incline bench press")) {
            setTextsExplanations("3 sets of 12 repetitions,1 min rest", R.drawable.incline_bench_press,
                    "https://www.youtube.com/watch?v=sDirS1jQuCc");
        } else if (exercise.equals("Lateral elevation")) {
            textViewExplanation.setText("4 sets of 10 repetitions,2 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=3VcKaXpzqRo");
        } else if (exercise.equals("Military Press")) {
            textViewExplanation.setText("5 sets of 8 repetitions,2 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=2yjwXTZQDDI");
        } else if (exercise.equals("Crunches")) {
            textViewExplanation.setText("3 sets of 50 repetitions,1 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=Xyd_fa5zoEU");
        } else if (exercise.equals("Pull up")) {
            textViewExplanation.setText("5 sets of 6 repetitions,3 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=eGo4IYlbE5g");
        } else if (exercise.equals("Rowing")) {
            textViewExplanation.setText("3 sets of 15 repetitions,1 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=kBWAon7ItDw");
        } else if (exercise.equals("Rowing T-bar")) {
            textViewExplanation.setText("4 sets of 10 repetitions,2 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=j3Igk5nyZE4");
        } else if (exercise.equals("Curl biceps")) {
            textViewExplanation.setText("3 sets of 20 repetitions,1 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=ykJmrZ5v0Oo");
        } else if (exercise.equals("Squat")) {
            textViewExplanation.setText("5 sets of 10 repetitions,2 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=bEv6CCg2BC8");
        } else if (exercise.equals("Lunges")) {
            textViewExplanation.setText("4 sets of 15 repetitions,1 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=3XDriUn0udo");
        } else if (exercise.equals("Deadlift")) {
            textViewExplanation.setText("4 sets of 10 repetitions,2 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=ytGaGIn3SjE");
        } else if (exercise.equals("Hamstring curl")) {
            textViewExplanation.setText("3 sets of 20 repetitions,1 min rest");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=F488k67BTNo");
        } else if (exercise.equals("Rower")) {
            textViewExplanation.setText("15 min of rower");
            btnExplanationLink.setText("https://www.youtube.com/watch?v=w2hGNM4l5so");
        } else if (exercise.equals("Elliptical trainer")) {
            textViewExplanation.setText("25 min of Elliptical trainer");
        } else if (exercise.equals("Running")) {
            textViewExplanation.setText("15 min of running");
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
