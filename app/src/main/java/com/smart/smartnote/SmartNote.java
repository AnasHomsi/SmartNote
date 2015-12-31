package com.smart.smartnote;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SmartNote extends AppCompatActivity {
    DBAdapter MyDB;
    int i = 0;
    public ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openDB();
        PopulateListView();
    FloatingActionButton fab_AddNewNote = (FloatingActionButton) findViewById(R.id.fab);
        fab_AddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SmartNote.this, AddNote.class);
                startActivity(i);

            }
        });

    }

    private void PopulateListView() {

        Cursor cursor = MyDB.getAllRows();

        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {DBAdapter.KEY_Subject, DBAdapter.KEY_Body, };

        int[] toViewIDs = new int[]
                {R.id.item_subject, R.id.item_body};

        // Create adapter to may columns of the DB onto elemesnt in the UI.
            MCA myCursorAdapter =
            new MCA(
                        this,        // Context
                        R.layout.item_layout,    // Row layout template
                        cursor,                    // cursor (set of DB records to map)
                        fromFieldNames,            // DB Column names
                        toViewIDs                // View IDs to put information in
                );

        // Set the adapter for the list view
        myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }

    private void openDB() {
        MyDB = new DBAdapter(this);
        MyDB.open();
    }



}
