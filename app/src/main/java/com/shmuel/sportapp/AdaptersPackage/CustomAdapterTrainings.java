package com.shmuel.sportapp.AdaptersPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shmuel.sportapp.PagesPackage.TrainingsExerciseExplanationActivity;
import com.shmuel.sportapp.PagesPackage.TrainingsExercisesActivity;
import com.shmuel.sportapp.R;

import java.util.List;

public class CustomAdapterTrainings extends RecyclerView.Adapter<CustomAdapterTrainings.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivTraining;
        private final LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            ivTraining = itemView.findViewById(R.id.ivTraining);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    private final List<Integer> list_data;
    private final String type;
    private final LayoutInflater mInflater;

    public CustomAdapterTrainings(List<Integer> list_data, String type, Context context) {
        this.list_data = list_data;
        this.type = type;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_trainings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int listData = list_data.get(position);
        holder.ivTraining.setImageResource(listData);
        holder.linearLayout.setOnClickListener(v -> {
            if (type.equals("Days")) {
                Intent intent = new Intent(mInflater.getContext(), TrainingsExercisesActivity.class);
                intent.putExtra("day", listData);
                mInflater.getContext().startActivity(intent);
            } else if (type.equals("Exercises")) {
                Intent intent = new Intent(mInflater.getContext(), TrainingsExerciseExplanationActivity.class);
                intent.putExtra("exercise", listData);
                mInflater.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

}
