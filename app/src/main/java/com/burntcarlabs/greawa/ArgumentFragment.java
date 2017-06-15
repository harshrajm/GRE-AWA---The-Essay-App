package com.burntcarlabs.greawa;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.burntcarlabs.greawa.data.AwaContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArgumentFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    EssayListCursorAdapter essayListCursorAdapter;

    private  static final int ESSAY_LOADER = 0;


    public ArgumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_argument, container, false);

    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v = getView();

        ListView essayTopicsListView = (ListView) v.findViewById(R.id.list_argument);
        //displayDatabaseInfo();
        essayListCursorAdapter = new EssayListCursorAdapter(getContext(),null);

        View emptyView = v.findViewById(R.id.empty_view);
        essayTopicsListView.setEmptyView(emptyView);

        essayTopicsListView.setAdapter(essayListCursorAdapter);


        essayTopicsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Uri currentEssayUri = ContentUris.withAppendedId(AwaContract.AwaEssayEntry.CONTENT_URI, id);

                        Intent intent = new Intent(getActivity(), ReadEssayActivity.class);
                        intent.setData(currentEssayUri);
                        startActivity(intent);
                    }
                }
        );
        getActivity().getSupportLoaderManager().initLoader(ESSAY_LOADER,null,this);
        //getSupportLoaderManager().initLoader(ESSAY_LOADER,null,this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection ={AwaContract.AwaEssayEntry._ID,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TOPIC,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_ANSWER,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_TYPE,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_BOOKMARK,
                AwaContract.AwaEssayEntry.COLUMN_ESSAY_READ};

        String selection = AwaContract.AwaEssayEntry.COLUMN_ESSAY_TYPE +"=?";
        String[] selectionArgs = new String[]{String.valueOf(AwaContract.AwaEssayEntry.TOPIC_ARGUMENT)};

        return new CursorLoader(getContext(),
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
