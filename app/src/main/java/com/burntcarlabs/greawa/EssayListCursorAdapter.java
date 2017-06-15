package com.burntcarlabs.greawa;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.burntcarlabs.greawa.data.AwaContract;

/**
 * Created by Harshraj on 28-11-2016.
 */

public class EssayListCursorAdapter extends CursorAdapter {

    public EssayListCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String optiEssayTopic;

        TextView readTextView = (TextView) view.findViewById(R.id.read_status);
        TextView optiTopicTextView = (TextView) view.findViewById(R.id.essay_topic_optimized);

        int readColumnIndex = cursor.getColumnIndex(AwaContract.AwaEssayEntry.COLUMN_ESSAY_READ);
        int topicColumnIndex = cursor.getColumnIndex(AwaContract.AwaEssayEntry.COLUMN_ESSAY_TOPIC);

        int essayRead = cursor.getInt(readColumnIndex);
        String essayTopic = cursor.getString(topicColumnIndex);

        if (essayTopic.length() > 135){
         optiEssayTopic = essayTopic.substring(0,140)+"...";
        }else{
            optiEssayTopic = essayTopic;
        }

        if (essayRead == 1){
            readTextView.setBackgroundColor(Color.parseColor("#1B5E20"));
        }else{
            readTextView.setBackgroundColor(Color.parseColor("#F44336"));
            // #FFD54F
        }

        optiTopicTextView.setText(optiEssayTopic);





    }
}
