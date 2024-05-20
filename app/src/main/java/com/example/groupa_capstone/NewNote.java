package com.example.groupa_capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class NewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        EditText TITLE = findViewById(R.id.Titleeditxt);
        EditText NOTE = findViewById(R.id.notetxt);
        Button SAVE = findViewById(R.id.saveButton);
        Button BACK = findViewById(R.id.backButton);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewNote.this,MainActivity.class));
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = TITLE.getText().toString();
                String conents = NOTE.getText().toString();
                long DateandTime = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setText(conents);
                note.getDateandTime(DateandTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

    }

}