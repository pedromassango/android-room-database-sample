package com.pedromassango.androidroomdatabasesample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pedromassango.androidroomdatabasesample.data.Phrase
import com.pedromassango.androidroomdatabasesample.data.PhraseDao
import com.pedromassango.androidroomdatabasesample.data.PhraseRoomDatabase

class PhrasesViewModel(app: Application): AndroidViewModel(app) {

    private val database = PhraseRoomDatabase.getDatabase(app)
    private val phraseDao = database.phraseDao()

    fun populateLocalDatabasehrases(){
        MainActivity.phrases.forEach { it ->
            it.phrase = "${it.id} - ${it.phrase}"

            phraseDao.insert(it)
        }
    }

    val phrasesList: LiveData<PagedList<Phrase>> =
            LivePagedListBuilder(phraseDao.allPhrases(), 10
            )
                    .setBoundaryCallback(object: PagedList.BoundaryCallback<Phrase>(){
                        override fun onItemAtEndLoaded(itemAtEnd: Phrase) {
                            Log.i(TAG, "onItemAtEndLoaded")

                           populateLocalDatabasehrases()
                        }

                        override fun onZeroItemsLoaded() {
                            Log.i(TAG, "onZeroItemsLoaded")

                            populateLocalDatabasehrases()
                        }

                        override fun onItemAtFrontLoaded(itemAtFront: Phrase) {
                            Log.i(TAG, "onItemAtFrontLoaded")
                        }
                    })
                    .build()

    companion object {
        private const val TAG = "PhrasesViewModel"
    }
}