package com.example.root.classmanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 5/7/16.
 */
public class CmsDatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 4;

    public static final String DATABASE_NAME = "class.db";

    public CmsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CLASS_INFO_TABLE = "CREATE TABLE " +
                CmsDatabaseContract.ClassEntry.TABLE_NAME + " (" +
                CmsDatabaseContract.ClassEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_1 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_1 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_2 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_2 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_3 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_3 + " TEXT NOT NULL, " +
                CmsDatabaseContract.ClassEntry.COLUMN_DATE +  " REAL NOT NULL" + ");";
        db.execSQL(SQL_CREATE_CLASS_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CmsDatabaseContract.ClassEntry.TABLE_NAME);
        onCreate(db);
    }
}
