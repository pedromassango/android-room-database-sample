package com.pedromassango.androidroomdatabasesample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.pedromassango.androidroomdatabasesample.data.Phrase;
import com.pedromassango.androidroomdatabasesample.data.PhraseDao;
import com.pedromassango.androidroomdatabasesample.data.PhraseRoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // the DAO to access database
    private PhraseDao phraseDao;

    private TextView tvStatus;
    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        tvStatus = findViewById(R.id.tv_status);
        listView = findViewById(R.id.list_phrases);

        // setup Database and get DAO
        PhraseRoomDatabase database = PhraseRoomDatabase.getDatabase(this);
        phraseDao = database.phraseDao();

        // get all phrases
        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // button add clicked, show a Dialog
        if(item.getItemId() == R.id.action_add){
            showDialogNewWord();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Get all words from database
     * and show it in listView
     */
    private void populateList(){
        List<Phrase> phrases = phraseDao.getAllPhrases();

        // if no words, stop here
        // update status text
        if(phrases.isEmpty()){
            tvStatus.setText(R.string.empty);
            return;
        }else{
            tvStatus.setText(String.format(getString(R.string.phrases_size), phrases.size()));
        }

        // convert to array
        List<String> phrasesAsStrings = new ArrayList<>();
        for (Phrase p:phrases) {
            phrasesAsStrings.add( p.getPhrase());
        }

        // show all phrases
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, phrasesAsStrings);

        listView.setAdapter(adapter);
    }

    /**
     * Show a input dialog to type a phrase
     * and add it in database
     */
    private void showDialogNewWord(){
        final EditText edtPhrase = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Type a phrase")
                .setView(edtPhrase)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = edtPhrase.getText().toString();

                        // if there is a text, save it
                        if(!text.trim().isEmpty()){
                            Phrase phrase = new Phrase( text);

                            // save in room database
                            phraseDao.insert(phrase);
                            // reload list, to reflect database changes
                            populateList();
                        }
                    }
                })
        .create()
        .show(); // show dialog
    }
}
