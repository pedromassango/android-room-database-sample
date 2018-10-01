package com.pedromassango.androidroomdatabasesample.data


import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.paging.PagedList

/**
 * Created by Pedro Massango on 8/7/18.
 * The Data Access Object for Phrase entity operations in database.
 */
@Dao
interface PhraseDao {

    /**
     * Get all phrases in database ordered by ASC
     * @return a list with all phrases
     */
    @Query("SELECT * FROM phrase_table ORDER BY Phrase ASC")
    fun allPhrases(): DataSource.Factory<Int, Phrase>

    @Query("SELECT * FROM phrase_table ORDER BY Phrase ASC")
    fun getAll(): List<Phrase>

    /**
     * Function to insert a phrase in room database
     * @param phrase to be inserted in database
     */
    @Insert
    fun insert(phrase: Phrase)

    /**
     * Function to delete an phrase in room database
     * @param phrase the object to be deleted
     */
    @Delete
    fun delete(phrase: Phrase)
}
