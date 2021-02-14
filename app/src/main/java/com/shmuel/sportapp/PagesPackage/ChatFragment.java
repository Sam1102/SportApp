package com.shmuel.sportapp.PagesPackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.shmuel.sportapp.AdaptersPackage.CustomAdapterChat;
import com.shmuel.sportapp.ModelsPackage.ChatModel;
import com.shmuel.sportapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ChatFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private final ArrayList<ChatModel> chatMessages = new ArrayList<ChatModel>();
    private ListenerRegistration chatRegistration;
    private CustomAdapterChat customAdapterChat;
    private RecyclerView recyclerView;
    private EditText etChat;
    private ImageView ivSend;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_chat, container, false);

        initUI();
        initListeners();
        initList();

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        chatRegistration.remove();
    }

    private void initUI() {
        recyclerView = mView.findViewById(R.id.recyclerView);
        etChat = mView.findViewById(R.id.etChat);
        ivSend = mView.findViewById(R.id.ivSend);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    private void initListeners() {
        ivSend.setOnClickListener(this);
    }

    private void initList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));

        listenForChatMessages();
    }

    private void listenForChatMessages() {
        chatRegistration = firestore.collection("messages").limit(50)
                .addSnapshotListener((documentSnapshots, e) -> {
                    if (e != null) {
                        Toast.makeText(mView.getContext(), "Error: " + e, Toast.LENGTH_LONG).show();
                        return;
                    }

                    chatMessages.clear();

                    for (DocumentSnapshot doc : documentSnapshots) {
                        ChatModel chatModel = doc.toObject(ChatModel.class);
                        chatModel.setId(doc.getId());
                        chatMessages.add(chatModel);
                    }

                    Collections.sort(chatMessages, (lhs, rhs) -> lhs.getTimestamp().compareTo(rhs.getTimestamp()));
                    customAdapterChat = new CustomAdapterChat(chatMessages, user.getUid(), mView.getContext());
                    recyclerView.setAdapter(customAdapterChat);
                });
    }

    private void sendChatMessage() {
        String message = etChat.getText().toString();
        if (!message.equals("")) {

            ChatModel chatModel = new ChatModel(message, user.getEmail(), user.getUid(), Calendar.getInstance().getTime());

            firestore.collection("messages")
                    .add(chatModel)
                    .addOnSuccessListener(documentReference -> etChat.setText(""))
                    .addOnFailureListener(e -> Toast.makeText(mView.getContext(), "Error: " + e, Toast.LENGTH_LONG).show());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSend:
                sendChatMessage();
                break;
        }
    }

}
