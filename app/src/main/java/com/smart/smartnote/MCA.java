package com.smart.smartnote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MCA extends ArrayAdapter<Note> {
    DBAdapter MyDB;
    private final Context context;

    public MCA(Context context, int layout, ArrayList<Note> notes) {
        super(context, layout,notes);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Note note = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.item_subject);
        TextView body = (TextView) convertView.findViewById(R.id.item_body);
        // Populate the data into the template view using the data object
        title.setText(note.NoteSubject);
        body.setText(note.NoteBody);
        // Return the completed view to render on screen

        Button bb = (Button) convertView.findViewById(R.id.item_delete);
        bb.setTag(note.RecID);
        bb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                alertTwoButtons(v,position);

            }
        });

        return convertView;

    }

    public void alertTwoButtons(final View v,final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Two Buttons")
                .setMessage(MCA.this.getItem(position).NoteSubject+" note will be deleted, Are you sure?")
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyDB = new DBAdapter(context);
                                MyDB.open();
                                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
                                MyDB.deleteRow((Long) v.getTag());
                                MCA.this.notifyDataSetChanged();
                                MCA.this.remove(getItem(position));
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

}