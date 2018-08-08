package com.pedromassango.androidroomdatabasesample.data;



import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Pedro Massango on 8/7/18.
 * The Data Access Object for Phrase entity operations in database.
 */
@Dao
public interface PhraseDao {

    /**
     * Function to insert a phrase in room database
     * @param phrase to be inserted in database
     */
    @Insert
    void insert(Phrase phrase);

    /**
     * Function to delete an phrase in room database
     * @param phrase the object to be deleted
     */
    @Delete
    void delete(Phrase phrase);

    /**
     * Get all phrases in database ordered by ASC
     * @return a list with all phrases
     */
    @Query("SELECT * FROM phrase_table ORDER BY mPhrase ASC")
    List<Phrase> getAllPhrases();
}
