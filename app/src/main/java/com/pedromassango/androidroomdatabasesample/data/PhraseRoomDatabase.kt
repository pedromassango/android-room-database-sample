package com.pedromassango.androidroomdatabasesample.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Pedro Massango on 8/7/18.
 */
@Database(entities = [Phrase::class], version = 2)
abstract class PhraseRoomDatabase : RoomDatabase() {

    /**
     * This is the Phrase data access object instance
     * @return the dao to phrase database operations
     */
    abstract fun phraseDao(): PhraseDao

    companion object {

        /**
         * This is just for singleton pattern
         */
        private var INSTANCE: PhraseRoomDatabase? = null

        fun getDatabase(context: Context): PhraseRoomDatabase {
            if (INSTANCE == null) {
                synchronized(PhraseRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Get PhraseRoomDatabase database instance
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PhraseRoomDatabase::class.java, "phrase_database"
                        )
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
