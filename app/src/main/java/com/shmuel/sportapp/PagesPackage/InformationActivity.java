package com.shmuel.sportapp.PagesPackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shmuel.sportapp.R;

import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText firstName, lastName, age, weight, height;
    private Spinner spinnerGender, spinnerLevel, spinnerPurpose;
    private final String[] gender_arrays = {"Man", "Woman"};
    private final String[] level_arrays = {"Beginner", "Intermediate", "Advanced"};
    private final String[] purpose_arrays = {"Weight loss", "Getting in shape", "Taking muscle"};
    private FirebaseFirestore fireStoreDB;
    private FirebaseUser currentFirebaseUser;
    private boolean isAfterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initUI();
        initListeners();
        initSpinner();
    }

    private void initUI() {
        isAfterLogin = getIntent().getBooleanExtra("isAfterLogin", true);

        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        spinnerPurpose = findViewById(R.id.spinnerPurpose);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        btnSave = findViewById(R.id.btnSave);

        fireStoreDB = FirebaseFirestore.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (isAfterLogin) {
            checkFullData();
        } else {
            readDataNotAfterLogin();
        }
    }

    private void initListeners() {
        btnSave.setOnClickListener(this);
    }

    private void readDataNotAfterLogin() {
        fireStoreDB.collection("trainings")
                .document(currentFirebaseUser.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            firstName.setText(document.getString("firstName"));
                            lastName.setText(document.getString("lastName"));
                            age.setText(document.getString("age"));
                            weight.setText(document.getString("weight"));
                            height.setText(document.getString("height"));

                            createSpinner(new String[]{document.getString("gender")}, spinnerGender);
                            createSpinner(new String[]{document.getString("level")}, spinnerLevel);
                            createSpinner(new String[]{document.getString("purpose")}, spinnerPurpose);
                        }
                    }
                });

        spinnerGender.setOnTouchListener((v, event) -> {
            createSpinner(gender_arrays, spinnerGender);
            return false;
        });

        spinnerLevel.setOnTouchListener((v, event) -> {
            createSpinner(level_arrays, spinnerLevel);
            return false;
        });

        spinnerPurpose.setOnTouchListener((v, event) -> {
            createSpinner(purpose_arrays, spinnerPurpose);
            return false;
        });
    }

    private void initSpinner() {
        createSpinner(gender_arrays, spinnerGender);
        createSpinner(level_arrays, spinnerLevel);
        createSpinner(purpose_arrays, spinnerPurpose);
    }

    private void createSpinner(String[] strings, Spinner spinner) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, strings);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void checkFields() {
        if (firstName.getText().toString().isEmpty()) {
            firstName.setError("Required field");
            firstName.requestFocus();
        }

        if (lastName.getText().toString().isEmpty()) {
            lastName.setError("Required field");
            lastName.requestFocus();
        }

        if (age.getText().toString().isEmpty()) {
            age.setError("Required field");
            age.requestFocus();
        }

        if (weight.getText().toString().isEmpty()) {
            weight.setError("Required field");
            weight.requestFocus();
        }

        if (height.getText().toString().isEmpty()) {
            height.setError("Required field");
            height.requestFocus();
        }
    }

    private void clickBtnSave() {
        checkFields();

        if (!firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() && !age.getText().toString().isEmpty() &&
                !weight.getText().toString().isEmpty() && !height.getText().toString().isEmpty()) {
            Map<String, Object> user = new HashMap<>();
            user.put("firstName", firstName.getText().toString());
            user.put("lastName", lastName.getText().toString());
            user.put("gender", spinnerGender.getSelectedItem().toString());
            user.put("age", age.getText().toString());
            user.put("weight", weight.getText().toString());
            user.put("height", height.getText().toString());
            user.put("level", spinnerLevel.getSelectedItem().toString());
            user.put("purpose", spinnerPurpose.getSelectedItem().toString());

            fireStoreDB.collection("trainings")
                    .document(currentFirebaseUser.getEmail())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        Intent intentAddInternetToMain = new Intent(InformationActivity.this, MainActivity.class);
                        startActivity(intentAddInternetToMain);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(InformationActivity.this, "Error: " + e, Toast.LENGTH_LONG).show());
        }
    }

    private void checkFullData() {
        DocumentReference docIdRef = fireStoreDB.collection("trainings")
                .document(currentFirebaseUser.getEmail());
        docIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                assert document != null;
                if (document.exists()) {
                    Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(InformationActivity.this, "Error: " + task.getException(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                clickBtnSave();
                break;
        }
    }

}
