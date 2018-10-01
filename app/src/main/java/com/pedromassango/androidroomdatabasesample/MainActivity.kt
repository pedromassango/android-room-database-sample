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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

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

        val viewModel = ViewModelProviders.of(this).get(PhrasesViewModel::class.java)

        // start listening for database changes
        viewModel.phrasesList.observe(this, Observer{data ->

            // if no words, stop here
            // update status text
            if (data.isEmpty()) {
                tvStatus!!.setText(R.string.empty)
                return@Observer
            } else {
                tvStatus!!.text = String.format(getString(R.string.phrases_size), data.size)
            }

            // show data
            adapter?.submitList(data)
        })
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
                    }
                }
                .create()
                .show() // show dialog
    }

    companion object {
        val phrases = arrayListOf(
                Phrase("Course"),
                Phrase("God"),
                Phrase("Jesus"),
                Phrase("Macbook"),
                Phrase("Windows 7"),
                Phrase("Windows 10"),
                Phrase("Windows XP"),
                Phrase("Windows 8"),
                Phrase("Wi"),
                Phrase("Playstation"),
                Phrase("Nitendo"),
                Phrase("Pro"),
                Phrase("BMW ZX"),
                Phrase("BMW OZ"),
                Phrase("BMW QW"),
                Phrase("AMMER 900"),
                Phrase("ZERO 97"),
                Phrase("ZERO 80"),
                Phrase("Pedro Massango"),
                Phrase("Pedro"),
                Phrase("Massango"),
                Phrase("Emilia")
        )
    }
}
