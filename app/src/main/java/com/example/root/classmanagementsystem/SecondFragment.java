package com.example.root.classmanagementsystem;

import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.classmanagementsystem.database.CmsDatabaseContract;

/**
 * Created by root on 7/7/16.
 */
public class SecondFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    protected RecyclerView mRecyclerView;
    protected SecondAdapter mSecondAdapter;
    private static final int VERTICAL_ITEM_SPACE = 20;
    private static final int HORIZONTAL_ITEM_SPACE = 16;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLoaderManager().initLoader(0,null,this);
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_second_week);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(HORIZONTAL_ITEM_SPACE));
        mSecondAdapter = new SecondAdapter(getContext());
        mRecyclerView.setAdapter(mSecondAdapter);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String whereArgument = CmsDatabaseContract.ClassEntry.COLUMN_DATE + " >= ? AND " +
                CmsDatabaseContract.ClassEntry.COLUMN_DATE + " < ? ";
        String[] whereArgs = new String[] {
                String.valueOf(Utility.getCurrentDateInLong()),
                String.valueOf(Utility.getThirdBreakPointDayInLong())
        };
        String orderBy = CmsDatabaseContract.ClassEntry.COLUMN_DATE + " ASC";
        CursorLoader cursorLoader = new CursorLoader(getActivity(), CmsDatabaseContract.
                ClassEntry.CONTENT_URI,Utility.CLASS_INFO_COLUMNS,whereArgument,whereArgs,orderBy);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mSecondAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mSecondAdapter.swapCursor(null);
    }

    /*
   Adding vertical spaces between CardViews
  */
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }

    /*
    Adding horizontal space in RecyclerView
    */
    public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mHorizontalSpaceHeight;

        public HorizontalSpaceItemDecoration(int mHorizontalSpaceHeight) {
            this.mHorizontalSpaceHeight = mHorizontalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = mHorizontalSpaceHeight;
            outRect.right = mHorizontalSpaceHeight;
        }
    }
}
