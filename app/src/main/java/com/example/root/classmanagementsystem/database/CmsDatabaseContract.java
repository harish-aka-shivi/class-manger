package com.example.root.classmanagementsystem.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by root on 4/7/16.
 */
public class CmsDatabaseContract {

    // Name for content provider
    public static final String CONTENT_AUTHORITY = "com.example.root.classmanagementsystem";

    //Address for content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //Path of the table to appended
    public static final String PATH_CLASS_INFO = "class_info";

    //Table for Subject Entry
    public static final class ClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "class_info";
        public static final String COLUMN_SUBJECT_1 = "subject_name_physics";
        public static final String COLUMN_TEACHER_1 = "teacher_name_physics";
        public static final String COLUMN_SUBJECT_2 = "subject_name_chemistry";
        public static final String COLUMN_TEACHER_2 = "teacher_name_chemistry";
        public static final String COLUMN_SUBJECT_3 = "subject_name_maths";
        public static final String COLUMN_TEACHER_3 = "teacher_name_maths";
        public static final String COLUMN_DATE = "date";

        public static Uri buildClassUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.
                buildUpon().appendPath(PATH_CLASS_INFO).build();

        public static final String CONTENT_TYPE = ContentResolver.
                CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CLASS_INFO;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.
                CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CLASS_INFO;


    }

}
