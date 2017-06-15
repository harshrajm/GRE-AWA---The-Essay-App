package com.burntcarlabs.greawa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.burntcarlabs.greawa.data.AwaContract.AwaEssayEntry;

import com.burntcarlabs.greawa.data.AwaContract;

import static android.R.attr.y;
import static android.R.string.yes;

public class ReadEssayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private Uri currentEssayUri;

    private TextView essayTopicText;
    private TextView essayAnsText;
    private FloatingActionButton fab;

    private int bookmark = 2;
    private int read = 2;

    private  static final int AWA_LOADER = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_essay);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v("Read ::::", String.valueOf(read));

                if(read == AwaEssayEntry.READ_YES){

                    fab.setImageResource(R.drawable.ic_done_black_24dp);
                    //write code to update read to 0
                    markEssayNotRead();
                    Toast.makeText(getApplicationContext(), "Essay marked un-Read", Toast.LENGTH_SHORT).show();
                    read = AwaEssayEntry.READ_NO;
                    return;
                }
                if(read == AwaEssayEntry.READ_NO){

                    fab.setImageResource(R.drawable.ic_clear_black_24dp);
                    // write code to update read to 1
                     markEssayRead();
                    Toast.makeText(getApplicationContext(), "Essay marked as Read", Toast.LENGTH_SHORT).show();

                    read = AwaEssayEntry.READ_YES;
                    finish();

                    return;


                }


            }
        });

        essayTopicText = (TextView) findViewById(R.id.essay_topic);
        essayAnsText = (TextView) findViewById(R.id.essay_answer);


        Intent intent = getIntent();
        currentEssayUri = intent.getData();
        if(currentEssayUri != null){
            Toast.makeText(getApplicationContext(), currentEssayUri.toString(), Toast.LENGTH_SHORT).show();
            getSupportLoaderManager().initLoader(AWA_LOADER,null,this);
        }




    }

    private void markEssayNotRead() {
        ContentValues values = new ContentValues();
        values.put(AwaEssayEntry.COLUMN_ESSAY_READ,AwaEssayEntry.READ_NO);

        int rowsAffected = getContentResolver().update(currentEssayUri, values, null, null);

        Log.v("Essay Marked not read for uri "+ currentEssayUri , String.valueOf(rowsAffected));
    }

    private void markEssayRead() {

        ContentValues values = new ContentValues();
        values.put(AwaEssayEntry.COLUMN_ESSAY_READ,AwaEssayEntry.READ_YES);

        int rowsAffected = getContentResolver().update(currentEssayUri, values, null, null);

        Log.v("Essay Marked read for uri "+ currentEssayUri , String.valueOf(rowsAffected));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.

        getMenuInflater().inflate(R.menu.menu_essay_view, menu);

        if(bookmark == AwaEssayEntry.BOOKMARK_YES){
            menu.findItem(R.id.bookmark).setIcon(R.drawable.ic_bookmark_white_24dp);
        }
        if(bookmark == AwaEssayEntry.BOOKMARK_NO){
            menu.findItem(R.id.bookmark).setIcon(R.drawable.ic_bookmark_border_white_24dp);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.bookmark:

                if(bookmark == AwaEssayEntry.BOOKMARK_NO){
                    item.setIcon(R.drawable.ic_bookmark_white_24dp);
                    bookmark = AwaEssayEntry.BOOKMARK_YES;
                    Toast.makeText(getApplicationContext(), "Essay Bookmarked", Toast.LENGTH_SHORT).show();
                    //write code to update bookmark to 1
                    essayBookmark();
                    return true;
                }
                if(bookmark == AwaEssayEntry.BOOKMARK_YES){
                    item.setIcon(R.drawable.ic_bookmark_border_white_24dp);
                    bookmark = AwaEssayEntry.BOOKMARK_NO;
                    Toast.makeText(getApplicationContext(), "Essay Un-Bookmarked", Toast.LENGTH_SHORT).show();
                    //write code to update bookmark to 0
                   essayUnBookmark();
                    return true;
                }



                //displayDatabaseInfo();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void essayUnBookmark() {
        ContentValues values = new ContentValues();
        values.put(AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,AwaEssayEntry.BOOKMARK_NO);

        int rowsAffected = getContentResolver().update(currentEssayUri, values, null, null);

        Log.v("Essay UnBookmarked for uri "+ currentEssayUri , String.valueOf(rowsAffected));
    }

    private void essayBookmark() {

        ContentValues values = new ContentValues();
        values.put(AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,AwaEssayEntry.BOOKMARK_YES);

        int rowsAffected = getContentResolver().update(currentEssayUri, values, null, null);

        Log.v("Essay Bookmarked for uri "+ currentEssayUri , String.valueOf(rowsAffected));
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                AwaEssayEntry._ID,
                AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                AwaEssayEntry.COLUMN_ESSAY_TYPE,
                AwaEssayEntry.COLUMN_ESSAY_READ,
                AwaEssayEntry.COLUMN_ESSAY_BOOKMARK
        };

        return new CursorLoader(this,
                currentEssayUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data.moveToFirst()){
            int topicColumnIndex = data.getColumnIndex(AwaEssayEntry.COLUMN_ESSAY_TOPIC);
            int ansColumnIndex = data.getColumnIndex(AwaEssayEntry.COLUMN_ESSAY_ANSWER);
            int typeColumnIndex = data.getColumnIndex(AwaEssayEntry.COLUMN_ESSAY_TYPE);
            int readColumnIndex = data.getColumnIndex(AwaEssayEntry.COLUMN_ESSAY_READ);
            int bookmarkColumnIndex = data.getColumnIndex(AwaEssayEntry.COLUMN_ESSAY_BOOKMARK);

            String topic = data.getString(topicColumnIndex);
            String ans = data.getString(ansColumnIndex);
            int type = data.getInt(typeColumnIndex);
            read = data.getInt(readColumnIndex);
            bookmark = data.getInt(bookmarkColumnIndex);

            if(type == AwaEssayEntry.TOPIC_ARGUMENT ){
                setTitle("Argument Essay");
            }
            if(type == AwaEssayEntry.TOPIC_ISSUE ){
                setTitle("Issue Essay");
            }

            essayTopicText.setText(topic);
            essayAnsText.setText(ans);

            if (read == AwaEssayEntry.READ_NO){
                fab.setImageResource(R.drawable.ic_done_black_24dp);
                fab.setBackgroundColor(Color.parseColor("#76FF03"));
            }
            if (read == AwaEssayEntry.READ_YES){
                fab.setImageResource(R.drawable.ic_clear_black_24dp);
                fab.setBackgroundColor(Color.parseColor("#F44336"));
            }

            if (bookmark == AwaEssayEntry.BOOKMARK_NO){


            }
            if (bookmark == AwaEssayEntry.BOOKMARK_YES){

            }


            Log.v("ReadEssay Activity topic",topic);
            Log.v("ReadEssay Activity ans",ans);
            Log.v("ReadEssay Activity type", String.valueOf(type));
            Log.v("ReadEssay Activity read", String.valueOf(read));
            Log.v("ReadEssay Activity bookmark", String.valueOf(bookmark));



        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        essayTopicText.setText("");
        essayAnsText.setText("");

    }
}
