package com.shmuel.sportapp.PagesPackage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.shmuel.sportapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class DataFragment extends Fragment implements View.OnClickListener {

    private FirebaseFirestore fireStoreDB;
    private ListenerRegistration fireStoreListener;
    private FirebaseAuth mAuth;
    private FirebaseUser currentFirebaseUser;
    private double mb, protein, fat, carbs;
    private Button btnDiet, btnTrainings, btnChat;
    private String level, purpose;
    private TextView tvUser;
    private ImageView ivLogout;
    private MCalendarView calendarView;
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
        calendarView = mView.findViewById(R.id.calendar);
        btnDiet = mView.findViewById(R.id.btnDiet);
        btnTrainings = mView.findViewById(R.id.btnTrainings);
        btnChat = mView.findViewById(R.id.btnChat);
        tvUser = mView.findViewById(R.id.tvUser);
        ivLogout = mView.findViewById(R.id.ivLogout);

        mAuth = FirebaseAuth.getInstance();
        fireStoreDB = FirebaseFirestore.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    private void initListeners() {
        btnDiet.setOnClickListener(this);
        btnTrainings.setOnClickListener(this);
        btnChat.setOnClickListener(this);
        ivLogout.setOnClickListener(this);
    }

    private void readData() {
        fireStoreListener = fireStoreDB.collection("trainings").document(currentFirebaseUser.getEmail())
                .addSnapshotListener((document, e) -> {
                    if (e != null) {
                        Toast.makeText(mView.getContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    tvUser.setText("Welcome " + document.getString("firstName") + " " + document.getString("lastName"));

                    btnDiet.setVisibility(View.VISIBLE);
                    btnTrainings.setVisibility(View.VISIBLE);
                    btnChat.setVisibility(View.VISIBLE);

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

                    List<String> arrayCalendar = (List<String>) document.get("calendar");
                    ArrayList<DateData> dates = new ArrayList<>();

                    if (arrayCalendar != null) {
                        for (int i = 0; i < arrayCalendar.size(); i++) {
                            String currentString = arrayCalendar.get(i);
                            String[] separated = currentString.split("/");
                            dates.add(new DateData(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]), Integer.parseInt(separated[2])));
                        }
                    }

                    calendarView.setOnDateClickListener(new OnDateClickListener() {
                        @Override
                        public void onDateClick(View view, DateData date) {
                            assert arrayCalendar != null;
                            arrayCalendar.add(date.getYear() + "/" + date.getMonth() + "/" + date.getDay());

                            Log.i("check1", date.getYear() + "/" + date.getMonth() + "/" + date.getDay());

                            Map<String, Object> user = new HashMap<>();
                            user.put("calendar", arrayCalendar);

                            fireStoreDB.collection("trainings")
                                    .document(currentFirebaseUser.getEmail())
                                    .update(user)
                                    .addOnSuccessListener(aVoid -> {
                                    })
                                    .addOnFailureListener(e -> Toast.makeText(mView.getContext(), "Error: " + e, Toast.LENGTH_LONG).show());
                        }
                    });

                    for (int i = 0; i < dates.size(); i++) {
                        calendarView.markDate(dates.get(i).getYear(), dates.get(i).getMonth(), dates.get(i).getDay());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDiet:
                Intent intentDiet = new Intent(mView.getContext(), DietActivity.class);
                intentDiet.putExtra("mb", (int) mb);
                intentDiet.putExtra("protein", (int) protein);
                intentDiet.putExtra("fat", (int) fat);
                intentDiet.putExtra("carbs", (int) carbs);
                startActivity(intentDiet);
                break;
            case R.id.btnTrainings:
                Intent intentTrainings = new Intent(mView.getContext(), TrainingsDaysActivity.class);
                intentTrainings.putExtra("level", level);
                intentTrainings.putExtra("purpose", purpose);
                startActivity(intentTrainings);
                break;
            case R.id.btnChat:
                Intent intentChat = new Intent(mView.getContext(), ChatFragment.class);
                startActivity(intentChat);
                break;
            case R.id.ivLogout:
                mAuth.signOut();

                Intent intentSignOut = new Intent(mView.getContext(), LoginActivity.class);
                startActivity(intentSignOut);
                break;
        }
    }

}
