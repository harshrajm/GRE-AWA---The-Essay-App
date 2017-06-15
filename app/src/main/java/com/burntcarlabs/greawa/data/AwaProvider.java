package com.burntcarlabs.greawa.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.burntcarlabs.greawa.data.AwaContract.AwaEssayEntry;
import java.security.Provider;

/**
 * Created by Harshraj on 26-11-2016.
 */

public class AwaProvider extends ContentProvider {

   private AwaDbHelper mDbHelper;

    public static final String LOG_TAG = AwaProvider.class.getSimpleName();


    private static final  int ESSAYS = 100;
    private static final  int ESSAY_ID = 101;

    private static final UriMatcher sUriMatcher  = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AwaContract.CONTENT_AUTHORITY, AwaContract.PATH_ESSAYS,ESSAYS);
        sUriMatcher.addURI(AwaContract.CONTENT_AUTHORITY,AwaContract.PATH_ESSAYS + "/#",ESSAY_ID);
    }



    @Override
    public boolean onCreate() {
        mDbHelper = new AwaDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,  String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor = null;

        int match = sUriMatcher.match(uri);

        switch (match){
            case ESSAYS:

                cursor = database.query(AwaEssayEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
                break;

            case ESSAY_ID:
                selection = AwaEssayEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(AwaEssayEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case ESSAYS:
                return insertEssay(uri, contentValues);
            default:
                throw new IllegalArgumentException("insertion is not supported for "+uri);
        }
    }

    private Uri insertEssay(Uri uri, ContentValues contentValues) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        long id = database.insert(AwaEssayEntry.TABLE_NAME,null,contentValues);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);

        switch (match){
            case ESSAYS:
                return updateEssay(uri, contentValues, selection, selectionArgs);
            case ESSAY_ID:
                selection = AwaEssayEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateEssay(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }

    }

    private int updateEssay(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(AwaEssayEntry.TABLE_NAME, contentValues,selection,selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
