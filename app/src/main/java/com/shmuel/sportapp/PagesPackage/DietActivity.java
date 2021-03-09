package com.shmuel.sportapp.PagesPackage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shmuel.sportapp.R;

public class DietActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView eggsG, cerealG, almondG, riceG, chickenG, cashewG, tunaG, pastaG, oliveOilG;
    private int mb, protein, fat, carbs;
    private Button btnDiet1, btnDiet2;
    private LinearLayout llDiet1, llDiet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        initUI();
        initListeners();
    }

    private void initUI() {
        mb = getIntent().getIntExtra("mb", 0);
        protein = getIntent().getIntExtra("protein", 0);
        fat = getIntent().getIntExtra("fat", 0);
        carbs = getIntent().getIntExtra("carbs", 0);

        btnDiet1 = findViewById(R.id.btnDiet1);
        btnDiet2 = findViewById(R.id.btnDiet2);
        llDiet1 = findViewById(R.id.llDiet1);
        llDiet2 = findViewById(R.id.llDiet2);
        eggsG = findViewById(R.id.eggsG);
        cerealG = findViewById(R.id.cerealG);
        almondG = findViewById(R.id.almondG);
        riceG = findViewById(R.id.riceG);
        chickenG = findViewById(R.id.chickenG);
        cashewG = findViewById(R.id.cashewG);
        tunaG = findViewById(R.id.tunaG);
        pastaG = findViewById(R.id.pastaG);
        oliveOilG = findViewById(R.id.oliveOilG);
    }

    private void setDataDiet1() {
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

    private void setDataDiet2() {
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

    private void initListeners() {
        btnDiet1.setOnClickListener(this);
        btnDiet2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDiet1:
                btnDiet1.setVisibility(View.GONE);
                btnDiet2.setVisibility(View.GONE);
                llDiet1.setVisibility(View.VISIBLE);

                setDataDiet1();
                break;
            case R.id.btnDiet2:
                btnDiet1.setVisibility(View.GONE);
                btnDiet2.setVisibility(View.GONE);
                llDiet2.setVisibility(View.VISIBLE);

                setDataDiet2();
                break;
        }
    }

}
