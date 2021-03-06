package com.pedromassango.androidroomdatabasesample.data


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Pedro Massango on 8/7/18.
 *
 * Entity class to store in Room Database
 */
@Entity(tableName = "phrase_table")
class Phrase(var phrase: String,
             @PrimaryKey(autoGenerate = true)
             val id: Int = 0)
