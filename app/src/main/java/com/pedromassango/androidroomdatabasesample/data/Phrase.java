package com.pedromassango.androidroomdatabasesample.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

/**
 * Created by Pedro Massango on 8/7/18.
 *
 * Entity class to store in Room Database
 */
@Entity(tableName = "phrase_table")
public class Phrase {

    @PrimaryKey
    @NonNull
    @android.support.annotation.NonNull
    private String mPhrase;

    public Phrase(String mPhrase){
        this.mPhrase = mPhrase;
    }

    public String getPhrase() {
        return mPhrase;
    }
}
