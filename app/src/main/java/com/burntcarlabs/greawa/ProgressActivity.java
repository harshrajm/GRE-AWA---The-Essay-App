package com.burntcarlabs.greawa;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.burntcarlabs.greawa.data.AwaContract;

public class ProgressActivity extends AppCompatActivity {

    private  static final int AWA_LOADER = 0;


    int totalEssays = 0;
    int totalEssaysRead = 0;
    int totalArgEssays = 0;
    int totalArgEssaysRead = 0;
    int totalIssueEssays = 0;
    int totalIssueEssaysRead = 0;

    private TextView percentageTextView;
    private TextView readOfTotalTextView;
    private TextView readOfArgTextView;
    private TextView readOfIssueTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);


        /*percentageTextView = (TextView) findViewById(R.id.percentage);
        readOfTotalTextView = (TextView) findViewById(R.id.essays_read);
        readOfArgTextView = (TextView) findViewById(R.id.arg_essays_read);
        readOfIssueTextView = (TextView) findViewById(R.id.issue_essays_read);

        Toast.makeText(getApplicationContext(), "In Progress Activity", Toast.LENGTH_SHORT).show();

        getSupportLoaderManager().initLoader(AWA_LOADER,null,this);*/

        setContentView(R.layout.activity_progress);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_progress, new ProgressFragment())
                .commit();
    }
/*
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection ={AwaContract.AwaEssayEntry._ID,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TYPE,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_READ};



        return new CursorLoader(this,
                AwaContract.AwaEssayEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        //if(data.moveToFirst()){

            while (data.moveToNext()){

                totalEssays++;

            int typeColumnIndex = data.getColumnIndex(AwaContract.AwaEssayEntry.COLUMN_ESSAY_TYPE);
            int readColumnIndex = data.getColumnIndex(AwaContract.AwaEssayEntry.COLUMN_ESSAY_READ);


            int type = data.getInt(typeColumnIndex);
            int read = data.getInt(readColumnIndex);

            if(read == AwaContract.AwaEssayEntry.READ_YES){
                totalEssaysRead++;

                if (type == AwaContract.AwaEssayEntry.TOPIC_ARGUMENT){
                    totalArgEssaysRead++;
                }
                if (type == AwaContract.AwaEssayEntry.TOPIC_ISSUE){
                    totalIssueEssaysRead++;
                }
            }

            if (type == AwaContract.AwaEssayEntry.TOPIC_ARGUMENT){
                totalArgEssays++;
            }else {
                totalIssueEssays++;
            }

                float percentageRead = (totalEssaysRead * 100.0f) / totalEssays;

                //long percentageRead = (totalEssaysRead/totalEssays)*100;
                percentageTextView.setText(String.format("%.1f", percentageRead)+"%");

                readOfTotalTextView.setText(totalEssaysRead+"/"+totalEssays);
                readOfArgTextView.setText(totalArgEssaysRead+"/"+totalArgEssays);
                readOfIssueTextView.setText(totalIssueEssaysRead+"/"+totalIssueEssays);

                Log.v("totalEssaysRead:", String.valueOf(totalEssaysRead));
                Log.v("totalEssays", String.valueOf(totalEssays));
                Log.v("percentageRead", String.valueOf(percentageRead));



        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
                percentageTextView.setText("");
                readOfTotalTextView.setText("");
                readOfArgTextView.setText("");
                readOfIssueTextView.setText("");
    }*/
}
