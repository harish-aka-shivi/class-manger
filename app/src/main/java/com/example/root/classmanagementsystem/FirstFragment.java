package com.example.root.classmanagementsystem;

import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {//@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected RecyclerView mRecyclerView;
    protected FirstAdapter mFirstAdapter;
    private static final int VERTICAL_ITEM_SPACE = 20;
    private static final int HORIZONTAL_ITEM_SPACE = 16;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLoaderManager().initLoader(0,null,this);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_first_week);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(HORIZONTAL_ITEM_SPACE));
        mFirstAdapter = new FirstAdapter(getContext());
        mRecyclerView.setAdapter(mFirstAdapter);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String whereArgument = CmsDatabaseContract.ClassEntry.COLUMN_DATE + " >= ? AND " +
                CmsDatabaseContract.ClassEntry.COLUMN_DATE + " < ? ";
        String[] whereArgs = new String[] {
            String.valueOf(Utility.getFirstDayInLong()),
            String.valueOf(Utility.getCurrentDateInLong())
        };
        String orderBy = CmsDatabaseContract.ClassEntry._ID + " ASC";

        CursorLoader cursorLoader = new CursorLoader(getActivity(), CmsDatabaseContract.
                ClassEntry.CONTENT_URI,Utility.CLASS_INFO_COLUMNS,whereArgument,whereArgs,orderBy);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mFirstAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mFirstAdapter.swapCursor(null);
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


