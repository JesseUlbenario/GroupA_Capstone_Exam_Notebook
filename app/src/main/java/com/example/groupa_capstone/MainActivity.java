package com.example.groupa_capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button newnote = findViewById(R.id.newnoteButton);

        newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewNote.class));
            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notelist = realm.where(Note.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thisAdaptor thisAdaptor = new thisAdaptor(getApplicationContext(),notelist);
        recyclerView.setAdapter(thisAdaptor);

        notelist.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                thisAdaptor.notifyDataSetChanged();
            }
        });
    }

}