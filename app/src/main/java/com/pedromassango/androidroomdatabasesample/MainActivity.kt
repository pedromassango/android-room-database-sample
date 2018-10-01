package com.pedromassango.androidroomdatabasesample

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

import com.pedromassango.androidroomdatabasesample.data.Phrase
import com.pedromassango.androidroomdatabasesample.data.PhraseDao
import com.pedromassango.androidroomdatabasesample.data.PhraseRoomDatabase

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // the DAO to access database
    private var phraseDao: PhraseDao? = null

    private var tvStatus: TextView? = null
    //private ListView listView ;
    private var recyclerView: RecyclerView? = null
    private var adapter: WordsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        tvStatus = findViewById(R.id.tv_status)
        //listView = findViewById(R.id.list_phrases);
        recyclerView = findViewById(R.id.list_phrases)
        adapter = WordsRecyclerAdapter()
        recyclerView!!.adapter = adapter
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        // setup Database and get DAO
        val database = PhraseRoomDatabase.getDatabase(this)
        phraseDao = database.phraseDao()

        // get all phrases
        populateList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // button add clicked, show a Dialog
        if (item.itemId == R.id.action_add) {
            showDialogNewWord()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Get all words from database
     * and show it in listView
     */
    private fun populateList() {
        val phrases = phraseDao!!.allPhrases

        // if no words, stop here
        // update status text
        if (phrases.isEmpty()) {
            tvStatus!!.setText(R.string.empty)
            return
        } else {
            tvStatus!!.text = String.format(getString(R.string.phrases_size), phrases.size)
        }

        /* // convert to array
        List<String> phrasesAsStrings = new ArrayList<>();
        for (Phrase p:phrases) {
            phrasesAsStrings.add( p.getPhrase());
        }*/

        // show all phrases
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, phrasesAsStrings);
                */

        adapter!!.add(phrases)
    }

    /**
     * Show a input dialog to type a phrase
     * and add it in database
     */
    private fun showDialogNewWord() {
        val edtPhrase = EditText(this)

        AlertDialog.Builder(this)
                .setTitle("Type a phrase")
                .setView(edtPhrase)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save") { dialogInterface, i ->
                    val text = edtPhrase.text.toString()

                    // if there is a text, save it
                    if (!text.trim { it <= ' ' }.isEmpty()) {
                        val phrase = Phrase(text)

                        // save in room database
                        phraseDao!!.insert(phrase)
                        // reload list, to reflect database changes
                        populateList()
                    }
                }
                .create()
                .show() // show dialog
    }
}
