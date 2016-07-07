package com.example.root.classmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.example.root.classmanagementsystem.database.CmsDatabaseContract;

/**
 * Created by root on 6/7/16.
 */
public class BulkInsertTask extends AsyncTask<Void,Void,Void> {
    private Context mContext;
    public BulkInsertTask(Context context){
        mContext = context;
    }
    @Override
    protected Void doInBackground(Void... params) {
        ContentValues[] cvArray = Utility.databaseFiller();
        int deletedLines = mContext.getContentResolver().delete(CmsDatabaseContract.
                ClassEntry.CONTENT_URI,null,null);
        int insertedLines = mContext.getContentResolver().bulkInsert(CmsDatabaseContract.
                ClassEntry.CONTENT_URI,cvArray);
        System.out.println("Lines deleted" + deletedLines);
        System.out.println("Inserted lines in dayta base");
        System.out.println("Inserted lines " + insertedLines);
        return null;
    }
}
