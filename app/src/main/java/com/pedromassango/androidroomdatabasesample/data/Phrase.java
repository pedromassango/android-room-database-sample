package com.pedromassango.androidroomdatabasesample.data;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Pedro Massango on 8/7/18.
 *
 * Entity class to store in Room Database
 */
@Entity(tableName = "phrase_table")
public class Phrase {

    @PrimaryKey
    @NonNull
    private String mPhrase;

    public Phrase(String mPhrase){
        this.mPhrase = mPhrase;
    }

    public String getPhrase() {
        return mPhrase;
    }
}
