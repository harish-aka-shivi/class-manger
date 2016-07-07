package com.example.root.classmanagementsystem;

import android.content.ContentValues;

import com.example.root.classmanagementsystem.database.CmsDatabaseContract;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Created by root on 5/7/16.
 */
public class Utility {

    public final static long MILLI_SECONDS_IN_A_DAY = 86400000L;
    public final static long MILLI_SECONDS_IN_A_WEEK = 604800000L;
    public static final String SUBJECT_PHYSICS = "Physics";
    public static final String SUBJECT_CHEMISTRY = "Chemistry";
    public static final String SUBJECT_MATHS = "Maths";
    public static final String SUBJECT_ENGLISH = "English";
    public static final String PHYSICS_TEACHER = "Nikolas Tesla";
    public static final String CHEMISTRY_TEACHER = "Neil Bohr";
    public static final String MATHS_TEACHER = "Ramanujan";
    public static final String ENGLISH_TEACHER = "Shakespeare";


    public static final String[] CLASS_INFO_COLUMNS = {
            CmsDatabaseContract.ClassEntry.TABLE_NAME + "." + CmsDatabaseContract.ClassEntry._ID,
            CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_1,
            CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_1,
            CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_2,
            CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_2,
            CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_3,
            CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_3,
            CmsDatabaseContract.ClassEntry.COLUMN_DATE
    };

    static final int COL_CLASS_INFO_ID = 0;
    static final int COL_CLASS_INFO_SUBJECT_1 = 1;
    static final int COL_CLASS_INFO_TEACHER_1 = 2;
    static final int COL_CLASS_INFO_SUBJECT_2 = 3;
    static final int COL_CLASS_INFO_TEACHER_2 = 4;
    static final int COL_CLASS_INFO_SUBJECT_3 = 5;
    static final int COL_CLASS_INFO_TEACHER_3 = 6;
    static final int COL_CLASS_INFO_DATE = 7;


    public static ContentValues[] databaseFiller() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long currentTimeInMillis = gregorianCalendar.getTimeInMillis();
        long startDayTimeInMillis = currentTimeInMillis-MILLI_SECONDS_IN_A_WEEK;
        Vector<ContentValues> contentValuesVector = new Vector<>();

        for (int i = 0;i < 29;i++) {
            ContentValues cv = new ContentValues();
            long  l = startDayTimeInMillis + MILLI_SECONDS_IN_A_DAY *i;

            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_1,SUBJECT_PHYSICS);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_1,PHYSICS_TEACHER);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_2,SUBJECT_CHEMISTRY);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_2,CHEMISTRY_TEACHER);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_SUBJECT_3,SUBJECT_MATHS);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_TEACHER_3,MATHS_TEACHER);
            cv.put(CmsDatabaseContract.ClassEntry.COLUMN_DATE,l);
            contentValuesVector.add(cv);
        }
        ContentValues[] cVArray = new ContentValues[contentValuesVector.size()];
        contentValuesVector.toArray(cVArray);
        return cVArray;
    }

    public static final long getCurrentDateInLong() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long currentTimeInMillis = gregorianCalendar.getTimeInMillis();
        return currentTimeInMillis;
    }

    public static final long getFirstDayInLong() {
        long firstDayInLong = getCurrentDateInLong()-MILLI_SECONDS_IN_A_WEEK;
        return firstDayInLong;
    }

    public static final long getThirdBreakPointDayInLong() {
        long thirdBreakPointDayInLong = getCurrentDateInLong() + MILLI_SECONDS_IN_A_WEEK;
        return thirdBreakPointDayInLong;
    }

    public static final long getFourthBreakPointInLong() {
        return getCurrentDateInLong()+ 2*MILLI_SECONDS_IN_A_WEEK;
    }
    public static final long getFifthBreakPointInLong() {
        return getCurrentDateInLong() + 3*MILLI_SECONDS_IN_A_WEEK;
    }

    public static final String getProperDateFromLong(Long l) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        String requiredFormat = "" + calendar.get(Calendar.DATE) + ", " +
                getMonthFromCalenderInt(calendar.get(Calendar.MONTH));
        return requiredFormat;
    }

    public static String getMonthFromCalenderInt (int month) {
        switch (month) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "July";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return "Not valid";
        }
    }

}
