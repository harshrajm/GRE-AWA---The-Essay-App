package com.burntcarlabs.greawa.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Harshraj on 17-11-2016.
 */

public final class AwaContract {

    private AwaContract(){}

    public static final String CONTENT_AUTHORITY = "com.burntcarlabs.greawa";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ESSAYS = "essays";



    public static final class AwaEssayEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ESSAYS);

        //TABLE NAME
        public final static String TABLE_NAME = "essays";

        //TABLE COLUMNS
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ESSAY_TYPE = "type";
        public final static String COLUMN_ESSAY_TOPIC ="topic";
        public final static String COLUMN_ESSAY_ANSWER ="answer";
        public final static String COLUMN_ESSAY_READ ="read";
        public final static String COLUMN_ESSAY_BOOKMARK = "bookmark";


        //TYPE VALUES
        public final static int TOPIC_ISSUE = 0;
        public final static int TOPIC_ARGUMENT = 1;

        //READ VALUES
        public final static int READ_NO = 0;
        public final static int READ_YES = 1;

        //BOOKMARK VALUES
        public final static int BOOKMARK_NO = 0;
        public final static int BOOKMARK_YES = 1;
    }

}
