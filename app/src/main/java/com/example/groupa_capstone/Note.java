package com.example.groupa_capstone;

import io.realm.RealmObject;

public class Note extends RealmObject{

    String Title;
    String Text;
    long DateandTime;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public long getDateandTime(long dateandTime) {
        return DateandTime;
    }

    public void setDateandTime(long dateandTime) {
        DateandTime = dateandTime;
    }
}
