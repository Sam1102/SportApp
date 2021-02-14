package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.shmuel.sportapp.R;

public class DietActivity extends AppCompatActivity {

    private TextView tvMb, tvProtein, tvFat, tvCarbs, eggsG, cerealG, almondG, riceG, chickenG, cashewG, tunaG, pastaG, oliveOilG;
    private int mb, protein, fat, carbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        initUI();
    }

    private void initUI() {
        mb = getIntent().getIntExtra("mb", 0);
        protein = getIntent().getIntExtra("protein", 0);
        fat = getIntent().getIntExtra("fat", 0);
        carbs = getIntent().getIntExtra("carbs", 0);

        tvMb = findViewById(R.id.tvMb);
        tvProtein = findViewById(R.id.tvProtein);
        tvFat = findViewById(R.id.tvFat);
        tvCarbs = findViewById(R.id.tvCarbs);
        eggsG = findViewById(R.id.eggsG);
        cerealG = findViewById(R.id.cerealG);
        almondG = findViewById(R.id.almondG);
        riceG = findViewById(R.id.riceG);
        chickenG = findViewById(R.id.chickenG);
        cashewG = findViewById(R.id.cashewG);
        tunaG = findViewById(R.id.tunaG);
        pastaG = findViewById(R.id.pastaG);
        oliveOilG = findViewById(R.id.oliveOilG);

        Spannable wordToSpanMb = new SpannableString("You need to eat\n" + mb + " Calories");
        wordToSpanMb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_700)), 16, 16 + String.valueOf(mb).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMb.setText(wordToSpanMb);

        Spannable wordToSpanProtein = new SpannableString(protein + "\nProtein");
        wordToSpanProtein.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_700)), 0, String.valueOf(protein).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvProtein.setText(wordToSpanProtein);

        Spannable wordToSpanFat = new SpannableString(fat + "\nFat");
        wordToSpanFat.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_700)), 0, String.valueOf(fat).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvFat.setText(wordToSpanFat);

        Spannable wordToSpanCarbs = new SpannableString(carbs + "\nCarbs");
        wordToSpanCarbs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_700)), 0, String.valueOf(carbs).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCarbs.setText(wordToSpanCarbs);

        int proteinD3 = protein / 3, fatD3 = fat / 3, carbsD3 = carbs / 3;

        int eggsD3 = (int) (proteinD3 * 9.09 / 65);
        eggsG.setText(eggsD3 + "units");

        int cerealD3 = (int) (carbsD3 * 1.28);
        cerealG.setText(cerealD3 + "g");

        int almondD3 = (int) (fatD3 * 1.6);
        almondG.setText(almondD3 + "g");

        int riceD3 = (int) (carbsD3 * 3.84);
        riceG.setText(riceD3 + "g");

        int chickenD3 = (int) (proteinD3 * 4.54);
        chickenG.setText(chickenD3 + "g");

        int cashewD3 = (int) (fatD3 * 1.6);
        cashewG.setText(cashewD3 + "g");

        int tunaD3 = (int) (proteinD3 * 4.4);
        tunaG.setText(tunaD3 + "g");

        int pastaD3 = (int) (carbsD3 * 4.4);
        pastaG.setText(pastaD3 + "g");

        oliveOilG.setText(fatD3 + "g");
    }

}
