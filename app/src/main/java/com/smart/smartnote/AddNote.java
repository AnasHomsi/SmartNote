package com.smart.smartnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    DBAdapter MyDB;
    long num_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        openDB();
        final EditText NoteSubject= (EditText) findViewById(R.id.NoteSubject);
        final EditText NoteBody= (EditText) findViewById(R.id.NoteBody);

        FloatingActionButton fab_Save = (FloatingActionButton) findViewById(R.id.fab_Save);
        FloatingActionButton fab_Cancel = (FloatingActionButton) findViewById(R.id.fab_Cancel);
        fab_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num_id=MyDB.insertRow(NoteSubject.getText().toString(), NoteBody.getText().toString());
                Intent i0 = new Intent(AddNote.this, SmartNote.class);
                i0.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i0);
                Toast.makeText(AddNote.this, num_id + " Saved", Toast.LENGTH_SHORT).show();

            }
        });

        fab_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i0=new Intent(AddNote.this,SmartNote.class);
                i0.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i0);
                Toast.makeText(AddNote.this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDB() {
        MyDB = new DBAdapter(this);
        MyDB.open();
    }
}
