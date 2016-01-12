package com.smart.smartnote;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                i.putExtra("NoteS", adapter.getItem(position).NoteSubject);
                i.putExtra("NoteB", adapter.getItem(position).NoteBody);
                i.putExtra("NoteI", adapter.getItem(position).RecID);
                startActivity(i);
            }
        });

    }

    private void PopulateListView() {
        adapter = new MCA(this, R.layout.item_layout, arrayOfnotes);
        Cursor cursor = MyDB.getAllRows();
        while (cursor.moveToNext()) {
            //long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
            Long ID = Long.parseLong(cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_ROWID)));
            String Subject = cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_Subject));
            String Body = cursor.getString(cursor.getColumnIndex(DBAdapter.KEY_Body));
            Note note1 = new Note(ID, Subject, Body);
            adapter.add(note1);
        }

        // Attach the adapter to a ListView
        myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(adapter);

    }

    private void openDB() {
        MyDB = new DBAdapter(this);
        MyDB.open();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_menu, menu);
        myList.setTextFilterEnabled(true);
        MenuItem searchItem = menu.findItem(R.id.action_search1);

        SearchManager searchManager = (SearchManager) SmartNote.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SmartNote.this.getComponentName()));
        }
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    myList.clearTextFilter();
                } else {
                    myList.setFilterText(newText.toString());
                }
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }*/
}
