package com.burntcarlabs.greawa;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.burntcarlabs.greawa.data.AwaContract;

/**
 * Created by Harshraj on 05-12-2016.
 */

public class BookMarkActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    EssayListCursorAdapter essayListCursorAdapter;

    private  static final int ESSAY_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(getApplicationContext(), "In BookMarkActivity", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argument);


        /*ListView essayTopicsListView = (ListView) findViewById(R.id.list_argument);

        View emptyView = findViewById(R.id.empty_view);
        essayTopicsListView.setEmptyView(emptyView);

        //displayDatabaseInfo();
        essayListCursorAdapter = new EssayListCursorAdapter(this,null);

        essayTopicsListView.setAdapter(essayListCursorAdapter);


        essayTopicsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Uri currentEssayUri = ContentUris.withAppendedId(AwaContract.AwaEssayEntry.CONTENT_URI, id);

                        Intent intent = new Intent(BookMarkActivity.this, ReadEssayActivity.class);
                        intent.setData(currentEssayUri);
                        startActivity(intent);
                    }
                }
        );

        getSupportLoaderManager().initLoader(ESSAY_LOADER,null,this);*/

        //setContentView(R.layout.activity_argument);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_argument, new BookmarkFragment())
                .commit();

    }





    /*private void displayDatabaseInfo() {

        String[] projection ={AwaEssayEntry._ID,
                              AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                              AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                              AwaEssayEntry.COLUMN_ESSAY_TYPE,
                              AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,
                              AwaEssayEntry.COLUMN_ESSAY_READ};

        String selection = AwaEssayEntry.COLUMN_ESSAY_TYPE +"=?";
        String[] selectionArgs = new String[]{String.valueOf(AwaEssayEntry.TOPIC_ARGUMENT)};

        Cursor cursor = getContentResolver().query(AwaEssayEntry.CONTENT_URI,projection,selection,selectionArgs,null);



        ListView argumentListView = (ListView) findViewById(R.id.list_argument);
       EssayListCursorAdapter essayListCursorAdapter = new EssayListCursorAdapter(this,cursor);
       argumentListView.setAdapter(essayListCursorAdapter);
      }*/


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection ={AwaContract.AwaEssayEntry._ID,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TYPE,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_READ};

        String selection = AwaContract.AwaEssayEntry.COLUMN_ESSAY_BOOKMARK +"=?";
        String[] selectionArgs = new String[]{String.valueOf(AwaContract.AwaEssayEntry.BOOKMARK_YES)};

        return new CursorLoader(this,
                AwaContract.AwaEssayEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        essayListCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        essayListCursorAdapter.swapCursor(null);
    }
}
