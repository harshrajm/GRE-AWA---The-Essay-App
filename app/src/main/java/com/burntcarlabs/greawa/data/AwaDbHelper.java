package com.burntcarlabs.greawa.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.burntcarlabs.greawa.Essays;
import com.burntcarlabs.greawa.data.AwaContract.AwaEssayEntry;

import java.util.ArrayList;

/**
 * Created by Harshraj on 17-11-2016.
 */

public final class AwaDbHelper extends SQLiteOpenHelper {

     //SQLiteDatabase db1;

    //DB name
    private static final String DATABASE_NAME = "awa.db";
    //DB version
    private static final int DATABASE_VERSION = 1;

    public AwaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db1=db;

        String SQL_CREATE_AWA_TABLE =   "CREATE TABLE " + AwaEssayEntry.TABLE_NAME  + " ("
                                        + AwaEssayEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + AwaEssayEntry.COLUMN_ESSAY_TYPE + " INTEGER NOT NULL, "
                                        + AwaEssayEntry.COLUMN_ESSAY_TOPIC + " TEXT NOT NULL, "
                                        + AwaEssayEntry.COLUMN_ESSAY_ANSWER + " TEXT NOT NULL, "
                                        + AwaEssayEntry.COLUMN_ESSAY_READ + " INTEGER NOT NULL DEFAULT 0, "
                                        + AwaEssayEntry.COLUMN_ESSAY_BOOKMARK + " INTEGER NOT NULL DEFAULT 0 );";



        db.execSQL(SQL_CREATE_AWA_TABLE);

        insertInDatabase(db);


    }

    private void insertInDatabase(SQLiteDatabase db) {
        DataLoader dataLoader = new DataLoader();

        ArrayList<Essays> essayList = dataLoader.insertInArraylist();

        for (Essays x: essayList){

            Log.v( "Get essay type: ", String.valueOf(x.getEssayType()));
            Log.v("get essay topic: ", x.getTopic());
            Log.v("get essay answer: ",x.getAnswer());
            Log.v("-----------","-----------------");

            ContentValues values = new ContentValues();

            values.put(AwaEssayEntry.COLUMN_ESSAY_TOPIC,x.getTopic());
            values.put(AwaEssayEntry.COLUMN_ESSAY_ANSWER,x.getAnswer());
            values.put(AwaEssayEntry.COLUMN_ESSAY_TYPE,x.getEssayType());

            long returnMsg = db.insert(AwaEssayEntry.TABLE_NAME,null,values);
            Log.v("return Msg ", String.valueOf(returnMsg));
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
