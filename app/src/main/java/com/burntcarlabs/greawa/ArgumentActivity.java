package com.burntcarlabs.greawa;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.burntcarlabs.greawa.data.AwaContract;
import com.burntcarlabs.greawa.data.AwaContract.AwaEssayEntry;
import com.burntcarlabs.greawa.data.AwaDbHelper;
import com.burntcarlabs.greawa.data.AwaProvider;
import com.burntcarlabs.greawa.data.DataLoader;

import java.util.ArrayList;

import static android.R.attr.id;

public class ArgumentActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EssayListCursorAdapter essayListCursorAdapter;

    private  static final int ESSAY_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_argument);


        ListView essayTopicsListView = (ListView) findViewById(R.id.list_argument);
        //displayDatabaseInfo();
        essayListCursorAdapter = new EssayListCursorAdapter(this,null);

        View emptyView = findViewById(R.id.empty_view);
        essayTopicsListView.setEmptyView(emptyView);

        essayTopicsListView.setAdapter(essayListCursorAdapter);


        essayTopicsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Uri currentEssayUri = ContentUris.withAppendedId(AwaEssayEntry.CONTENT_URI, id);

                        Intent intent = new Intent(ArgumentActivity.this, ReadEssayActivity.class);
                        intent.setData(currentEssayUri);
                        startActivity(intent);
                    }
                }
        );

        getSupportLoaderManager().initLoader(ESSAY_LOADER,null,this);*/

        setContentView(R.layout.activity_argument);
                getSupportFragmentManager().beginTransaction()
                               .replace(R.id.activity_argument, new ArgumentFragment())
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
        String[] projection ={AwaEssayEntry._ID,
                AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                AwaEssayEntry.COLUMN_ESSAY_TYPE,
                AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,
                AwaEssayEntry.COLUMN_ESSAY_READ};

        String selection = AwaEssayEntry.COLUMN_ESSAY_TYPE +"=?";
        String[] selectionArgs = new String[]{String.valueOf(AwaEssayEntry.TOPIC_ARGUMENT)};

        return new CursorLoader(this,
                AwaEssayEntry.CONTENT_URI,
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
