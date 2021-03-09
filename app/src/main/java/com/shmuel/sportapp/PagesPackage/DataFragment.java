package com.shmuel.sportapp.PagesPackage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.shmuel.sportapp.R;

public class DataFragment extends Fragment implements View.OnClickListener {

    private FirebaseFirestore fireStoreDB;
    private ListenerRegistration fireStoreListener;
    private FirebaseAuth mAuth;
    private FirebaseUser currentFirebaseUser;
    private double mb, protein, fat, carbs;
    private String level, purpose;
    private TextView tvUser, tvMb, tvProtein, tvFat, tvCarbs;
    private ImageView ivLogout, ivDiet, ivTrainings, ivInformation;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_data, container, false);

        initUI();
        initListeners();
        readData();

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        fireStoreListener.remove();
    }

    private void initUI() {
        ivDiet = mView.findViewById(R.id.ivDiet);
        ivTrainings = mView.findViewById(R.id.ivTrainings);
        ivInformation = mView.findViewById(R.id.ivInformation);
        tvUser = mView.findViewById(R.id.tvUser);
        ivLogout = mView.findViewById(R.id.ivLogout);
        tvMb = mView.findViewById(R.id.tvMb);
        tvProtein = mView.findViewById(R.id.tvProtein);
        tvFat = mView.findViewById(R.id.tvFat);
        tvCarbs = mView.findViewById(R.id.tvCarbs);

        mAuth = FirebaseAuth.getInstance();
        fireStoreDB = FirebaseFirestore.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    private void initListeners() {
        ivDiet.setOnClickListener(this);
        ivTrainings.setOnClickListener(this);
        ivInformation.setOnClickListener(this);
        ivLogout.setOnClickListener(this);
    }

    private void readData() {
        fireStoreListener = fireStoreDB.collection("trainings")
                .document(currentFirebaseUser.getEmail())
                .addSnapshotListener((document, e) -> {
                    if (e != null) {
                        Toast.makeText(mView.getContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    tvUser.setText("Welcome " + document.getString("firstName") + " " + document.getString("lastName"));

                    ivDiet.setVisibility(View.VISIBLE);
                    ivTrainings.setVisibility(View.VISIBLE);

                    if (document.getString("gender").equals("Man")) {
                        mb = 13.707 * Double.parseDouble(document.getString("weight")) + 492.3 *
                                Double.parseDouble(document.getString("height")) / 100 - 6.673 *
                                Double.parseDouble(document.getString("age")) + 77.607;
                    } else if (document.getString("gender").equals("Woman")) {
                        mb = 9.740 * Double.parseDouble(document.getString("weight")) + 172.9 *
                                Double.parseDouble(document.getString("height")) / 100 - 4.737 *
                                Double.parseDouble(document.getString("age")) + 667.051;
                    }

                    if (document.getString("level").equals("Beginner")) {
                        mb = mb * 1.375;
                    } else if (document.getString("level").equals("Intermediate")) {
                        mb = mb * 1.55;
                    } else if (document.getString("level").equals("Advanced")) {
                        mb = mb * 1.725;
                    }

                    level = document.getString("level");
                    purpose = document.getString("purpose");

                    Log.i("check1", "mb: " + (int) mb);

                    protein = 1.8 * Double.parseDouble(document.getString("weight"));

                    Log.i("check1", "protein: " + (int) protein);

                    fat = Double.parseDouble(document.getString("weight"));

                    carbs = (mb - (protein * 4 + (int) fat * 9)) / 4;

                    Log.i("check1", "carbs: " + (int) carbs);

                    int mbInt = (int) mb;
                    Spannable wordToSpanMb = new SpannableString("Today you need to eat\n" + mbInt + " Calories");
                    wordToSpanMb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray)), 16, 16 + String.valueOf(mbInt).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvMb.setText(wordToSpanMb);

                    int proteinInt = (int) protein;
                    Spannable wordToSpanProtein = new SpannableString(proteinInt + "\nProtein");
                    wordToSpanProtein.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray)), 0, String.valueOf(proteinInt).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvProtein.setText(wordToSpanProtein);

                    int fatInt = (int) fat;
                    Spannable wordToSpanFat = new SpannableString(fatInt + "\nFat");
                    wordToSpanFat.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray)), 0, String.valueOf(fatInt).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvFat.setText(wordToSpanFat);

                    int carbsInt = (int) carbs;
                    Spannable wordToSpanCarbs = new SpannableString(carbsInt + "\nCarbs");
                    wordToSpanCarbs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray)), 0, String.valueOf(carbsInt).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvCarbs.setText(wordToSpanCarbs);
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDiet:
                Intent intentDiet = new Intent(mView.getContext(), DietActivity.class);
                intentDiet.putExtra("mb", (int) mb);
                intentDiet.putExtra("protein", (int) protein);
                intentDiet.putExtra("fat", (int) fat);
                intentDiet.putExtra("carbs", (int) carbs);
                startActivity(intentDiet);
                break;
            case R.id.ivTrainings:
                Intent intentTrainings = new Intent(mView.getContext(), TrainingsDaysActivity.class);
                intentTrainings.putExtra("level", level);
                intentTrainings.putExtra("purpose", purpose);
                startActivity(intentTrainings);
                break;
            case R.id.ivInformation:
                Intent intentInformation = new Intent(mView.getContext(), InformationActivity.class);
                intentInformation.putExtra("isAfterLogin", false);
                startActivity(intentInformation);
                break;
            case R.id.ivLogout:
                mAuth.signOut();

                Intent intentSignOut = new Intent(mView.getContext(), LoginActivity.class);
                startActivity(intentSignOut);
                break;
        }
    }

}
