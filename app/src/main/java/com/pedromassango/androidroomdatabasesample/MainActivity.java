package com.pedromassango.androidroomdatabasesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pedromassango.androidroomdatabasesample.data.Phrase;
import com.pedromassango.androidroomdatabasesample.data.PhraseDao;
import com.pedromassango.androidroomdatabasesample.data.PhraseRoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        TextView tvStatus = findViewById(R.id.tv_status);
        ListView listView = findViewById(R.id.list_phrases);

        // get all phrases
        PhraseRoomDatabase database = PhraseRoomDatabase.getDatabase(this);
        PhraseDao phraseDao = database.phraseDao();
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
}
