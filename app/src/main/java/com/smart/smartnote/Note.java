package com.smart.smartnote;

/**
 * Created by Smart on 12/31/2015.
 */
public class Note {
    public Long RecID;
    public String NoteSubject;
    public String NoteBody;

    public Note(Long RecID,String NoteSubject,String NoteBody)
    {
        this.RecID=RecID;
        this.NoteSubject=NoteSubject;
        this.NoteBody=NoteBody;
    }


}
