package com.shmuel.sportapp.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shmuel.sportapp.ModelsPackage.ChatModel;
import com.shmuel.sportapp.R;

import java.util.List;

public class CustomAdapterChat extends RecyclerView.Adapter<CustomAdapterChat.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewChatEmailSent, textViewChatSent, textViewChatEmailReceived, textViewChatReceived;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewChatEmailSent = itemView.findViewById(R.id.textViewChatEmailSent);
            textViewChatSent = itemView.findViewById(R.id.textViewChatSent);
            textViewChatEmailReceived = itemView.findViewById(R.id.textViewChatEmailReceived);
            textViewChatReceived = itemView.findViewById(R.id.textViewChatReceived);
        }
    }

    private final List<ChatModel> list_data;
    private final String uId;
    private final LayoutInflater mInflater;

    public CustomAdapterChat(List<ChatModel> list_data, String uId, Context context) {
        this.list_data = list_data;
        this.uId = uId;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ChatModel listData = list_data.get(position);

        if (listData.getUser().equals(uId)) {
            holder.textViewChatEmailSent.setText(listData.getEmail());
            holder.textViewChatSent.setText(listData.getText());
            holder.textViewChatReceived.setVisibility(View.GONE);
        } else {
            holder.textViewChatEmailReceived.setText(listData.getEmail());
            holder.textViewChatReceived.setText(listData.getText());
            holder.textViewChatSent.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

}
