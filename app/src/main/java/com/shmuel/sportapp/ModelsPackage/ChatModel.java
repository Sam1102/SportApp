package com.shmuel.sportapp.ModelsPackage;

import java.util.Date;

public class ChatModel {

    private String id;
    private String text;
    private String email;
    private String user;
    private Date timestamp;

    public ChatModel(String text, String email, String user, Date timestamp) {
        this.text = text;
        this.email = email;
        this.user = user;
        this.timestamp = timestamp;
    }

    public ChatModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
