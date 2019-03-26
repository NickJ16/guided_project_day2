package com.joshuahalvorson.journal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListActivity extends AppCompatActivity {
    public static final int EDIT_ENTRY_REQUEST_CODE = 2;
    public static final int NEW_ENTRY_REQUEST_CODE = 1;

    private ArrayList<JournalEntry> journalEntries;

    private Context context;

    private LinearLayout entryList;

    public static int nextId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(getLocalClassName(), "onCreate");

        entryList = findViewById(R.id.entry_list);

        context = this;

        journalEntries = new ArrayList<>();
        addTestEntries();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JournalDetails.class);
                JournalEntry journalEntry = createJournalEntry();
                intent.putExtra(JournalEntry.TAG, journalEntry);
                startActivityForResult(intent, NEW_ENTRY_REQUEST_CODE);
            }
        });
    }

    public JournalEntry createJournalEntry(){
        JournalEntry journalEntry = new JournalEntry(nextId++);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        journalEntry.setDate(dateFormat.format(date));
        return journalEntry;
    }

    public JournalEntry createJournalEntry(String text){
        JournalEntry journalEntry = createJournalEntry();
        journalEntry.setEntryText(text);
        return journalEntry;
    }

    private TextView generateTextView(final JournalEntry entry){
        TextView textView = new TextView(context);
        textView.setText(String.format("Date: %s Rating: %d", entry.getDate(), entry.getDayRating()));
        textView.setTextColor(Color.BLACK);
        //textView.setTextSize(30);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(context, JournalDetails.class);
                detailsIntent.putExtra(JournalEntry.TAG, entry);
                startActivityForResult(detailsIntent, EDIT_ENTRY_REQUEST_CODE);
            }
        });
        return textView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getLocalClassName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getLocalClassName(), "onResume");
        entryList.removeAllViews();
        for(JournalEntry entry: journalEntries) {
            entryList.addView(generateTextView(entry));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getLocalClassName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(getLocalClassName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getLocalClassName(), "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == EDIT_ENTRY_REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                JournalEntry entry = (JournalEntry) data.getSerializableExtra(JournalEntry.TAG);
                journalEntries.set(entry.getId(), entry);
            }
        }else if(requestCode == NEW_ENTRY_REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                JournalEntry entry = (JournalEntry) data.getSerializableExtra(JournalEntry.TAG);
                journalEntries.add(entry);
            }
        }
    }

    private void addTestEntries() {
        journalEntries.add(createJournalEntry("Gathered by gravity how far away finite but unbounded the only home we've ever known network of wormholes Jean-François Champollion? Tendrils of gossamer clouds Orion's sword extraplanetary invent the universe trillion stirred by starlight. Shores of the cosmic ocean vastness is bearable only through love permanence of the stars astonishment a mote of dust suspended in a sunbeam extraplanetary. Made in the interiors of collapsing stars not a sunrise but a galaxyrise a very small stage in a vast cosmic arena a mote of dust suspended in a sunbeam something incredible is waiting to be known astonishment."));
        journalEntries.add(createJournalEntry("Vangelis muse about Hypatia explorations hundreds of thousands another world. Shores of the cosmic ocean a mote of dust suspended in a sunbeam colonies Tunguska event finite but unbounded shores of the cosmic ocean? Extraplanetary bits of moving fluff gathered by gravity a still more glorious dawn awaits not a sunrise but a galaxyrise with pretty stories for which there's little good evidence. Take root and flourish courage of our questions vastness is bearable only through love paroxysm of global death invent the universe something incredible is waiting to be known?"));
        journalEntries.add(createJournalEntry("Preserve and cherish that pale blue dot two ghostly white figures in coveralls and helmets are soflty dancing vastness is bearable only through love Euclid permanence of the stars inconspicuous motes of rock and gas. Dispassionate extraterrestrial observer something incredible is waiting to be known star stuff harvesting star light great turbulent clouds network of wormholes the only home we've ever known. Of brilliant syntheses emerged into consciousness vanquish the impossible vanquish the impossible hundreds of thousands dream of the mind's eye."));
        journalEntries.add(createJournalEntry("Extraplanetary Euclid Hypatia brain is the seed of intelligence intelligent beings Rig Veda. Vastness is bearable only through love circumnavigated emerged into consciousness white dwarf colonies something incredible is waiting to be known. Two ghostly white figures in coveralls and helmets are soflty dancing star stuff harvesting star light bits of moving fluff invent the universe concept of the number one the ash of stellar alchemy. The only home we've ever known invent the universe rich in heavy atoms concept of the number one muse about something incredible is waiting to be known."));
        journalEntries.add(createJournalEntry("Science dream of the mind's eye stirred by starlight Jean-François Champollion with pretty stories for which there's little good evidence circumnavigated? Sea of Tranquility extraordinary claims require extraordinary evidence the carbon in our apple pies the ash of stellar alchemy ship of the imagination preserve and cherish that pale blue dot. Sea of Tranquility hundreds of thousands ship of the imagination the sky calls to us invent the universe descended from astronomers and billions upon billions upon billions upon billions upon billions upon billions upon billions."));
    }

}
