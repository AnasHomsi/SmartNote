package com.smart.smartnote;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Smart on 12/31/2015.
 */
public class MCA extends ArrayAdapter<String> {
    DBAdapter MyDB;
    private final Context context;
    private Cursor c;
    private String[] from;
    private int[] to;

    public MCA(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout,c,from,to);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = super.getView(position,convertView,parent);
        Button bb= (Button) rowView.findViewById(R.id.item_delete);
        bb.setTag(this.notes.get(position).getId())
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDB = new DBAdapter(context);
                MyDB.open();
                MyDB.deleteRow(v.getTag());
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }
}