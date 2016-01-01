package com.smart.smartnote;

/**
 * Created by Smart on 12/31/2015.
 */
public class Note {
    private int RecID;
    private String NoteSubject;
    private String NoteBody;


    public Note(int RecID,String NoteSubject,String NoteBody)
    {
        this.RecID=RecID;
        this.NoteSubject=NoteSubject;
        this.NoteBody=NoteBody;
    }


}
