package com.example.root.classmanagementsystem;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Switch;

/**
 * Created by root on 6/7/16.
 */
public class CmsDatabaseProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private CmsDatabaseHelper cmsDatabaseHelper;
    static final int CLASS_INFO = 100;
    static final int CLASS_INFO_WITH_DATE = 101;


    static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sURIMatcher.addURI(CmsDatabaseContract.CONTENT_AUTHORITY,CmsDatabaseContract.PATH_CLASS_INFO,CLASS_INFO);
        sURIMatcher.addURI(CmsDatabaseContract.CONTENT_AUTHORITY,CmsDatabaseContract.PATH_CLASS_INFO+"/*",CLASS_INFO);
        //to be added other matchers
        return sURIMatcher;
    }

    @Override
    public boolean onCreate() {
        cmsDatabaseHelper = new CmsDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        retCursor = cmsDatabaseHelper.getReadableDatabase().query(CmsDatabaseContract.ClassEntry.TABLE_NAME,
                projection,selection,selectionArgs,null,null,sortOrder);
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match =sUriMatcher.match(uri);
        switch(match) {
            case CLASS_INFO:
                return CmsDatabaseContract.ClassEntry.CONTENT_TYPE;
            // to be added other cases
            default:
                //throw new UnsupportedOperationException("Unknown uri: " + uri);        }
                return CmsDatabaseContract.ClassEntry.CONTENT_TYPE;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri returnUri;
        long insert_id = cmsDatabaseHelper.getWritableDatabase().insert(CmsDatabaseContract
                .ClassEntry.TABLE_NAME,null,values);
        returnUri = CmsDatabaseContract.ClassEntry.buildClassUri(insert_id);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int linesDel;
        linesDel = cmsDatabaseHelper.getWritableDatabase().delete(CmsDatabaseContract.
                ClassEntry.TABLE_NAME,selection,selectionArgs);
        return linesDel;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase writableDatabase = cmsDatabaseHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues value : values) {
                //normalizeDate(value);
                long _id = writableDatabase.insert(CmsDatabaseContract.ClassEntry.TABLE_NAME, null, value);
                if (_id != -1) {
                    returnCount++;
                }
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnCount;
    }
}
