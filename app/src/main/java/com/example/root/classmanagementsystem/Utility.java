package com.example.root.classmanagementsystem;

import android.content.ContentValues;

import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Created by root on 5/7/16.
 */
public class Utility {

    public final static int SECONDS_IN_A_DAY = 86400;
    public static final String SUBJECT_PHYSICS = "Physics";
    public static final String SUBJECT_CHEMISTRY = "Chemistry";
    public static final String SUBJECT_MATHS = "Maths";
    public static final String SUBJECT_ENGLISH = "English";
    public static final String PHYSICS_TEACHER = "H.C Verma";
    public static final String CHEMISTRY_TEACHER = "O.P Sharma";
    public static final String MATHS_TEACHER = "Shrinivasan";
    public static final String ENGLISH_TEACHER = "Shakespeare";

    public static ContentValues[] databaseFiller() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long currentTimeInMillis = gregorianCalendar.getTimeInMillis();
        //Calendar calendar = Calendar.getInstance();
        long startDayTimeInMillis = currentTimeInMillis-604800000L;
        Vector<ContentValues> contentValuesVector = new Vector<>();

        for (int i = 0;i < 24;i++) {
            ContentValues cv = new ContentValues();
            long  l =startDayTimeInMillis + SECONDS_IN_A_DAY*i;
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_1,SUBJECT_PHYSICS);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_1,PHYSICS_TEACHER);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_2,SUBJECT_CHEMISTRY);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_2,CHEMISTRY_TEACHER);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_3,SUBJECT_MATHS);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_3,MATHS_TEACHER);
                cv.put(CmsDatabaseContract.ClassEntry.COLUMN_DATE,l);
                contentValuesVector.add(cv);
            }
        }
        ContentValues[] cVArray = new ContentValues[contentValuesVector.size()];
        contentValuesVector.toArray(cVArray);
        System.out.println("lenght of array" + cVArray.length);
        for (int k = 0;k <cVArray.length;k++) {
            System.out.println("cvArray[k]  " + cVArray[k].toString());
        }

        return cVArray;

    }

}
