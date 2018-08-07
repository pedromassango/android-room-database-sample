package com.pedromassango.androidroomdatabasesample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Pedro Massango on 8/7/18.
 */
@Database(entities = {Phrase.class}, version = 1)
public abstract class PhraseRoomDatabase extends RoomDatabase {

    /**
     * This is the Phrase data access object instance
     * @return the dao to phrase database operations
     */
    public abstract PhraseDao phraseDao();

    /**
     * This is just for singleton pattern
     */
    private static PhraseRoomDatabase INSTANCE;

    public static PhraseRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized(PhraseRoomDatabase.class){
                if(INSTANCE == null){
                    // Get PhraseRoomDatabase database instance
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhraseRoomDatabase.class, "phrase_database"
                    )
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
