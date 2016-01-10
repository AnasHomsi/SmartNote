package com.smart.smartnote;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SmartNote extends AppCompatActivity {
    DBAdapter MyDB;
    int i = 0;
    public ListView myList;
   public ArrayList<Note> arrayOfnotes = new ArrayList<Note>();
    MCA adapter;

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

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SmartNote.this, EditNote.class);
                //To pass:
                i.putExtra("NoteS",adapter.getItem(position).NoteSubject);
                i.putExtra("NoteB",adapter.getItem(position).NoteBody);
                i.putExtra("NoteI",adapter.getItem(position).RecID);

                startActivity(i);
            }
        });

    }

    private void PopulateListView() {
        adapter = new MCA(this,R.layout.item_layout, arrayOfnotes);
       Cursor cursor = MyDB.getAllRows();
        while (cursor.moveToNext()){
            //long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
           Long ID=Long.parseLong(cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_ROWID)));
            String Subject=cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_Subject));
            String Body=cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_Body));
            Note note1 = new Note(ID,Subject, Body);

            adapter.add(note1);
        }

/*
        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {DBAdapter.KEY_Subject, DBAdapter.KEY_Body,DBAdapter. };

        int[] toViewIDs = new int[]
                {R.id.item_subject, R.id.item_body};*/

        // Construct the data source
      // arrayOfnotes = new ArrayList<Note>();
        // Create the adapter to convert the array to views
        //MCA adapter = new MCA(this,R.layout.item_layout, arrayOfnotes);

        // Attach the adapter to a ListView
        myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(adapter);

    }

    private void openDB() {
        MyDB = new DBAdapter(this);
        MyDB.open();
    }

}
