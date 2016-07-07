package com.example.root.classmanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 7/7/16.
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder>{
    private Cursor mCursor;
    private Context mContext;

    public SecondAdapter (Context context) {
        mContext = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mSubject1;
        private final TextView mSubject2;
        private final TextView mSubject3;
        private final TextView mTeacher1;
        private final TextView mTeacher2;
        private final TextView mTeacher3;
        private final TextView mDate;

        public ViewHolder (final View view, final SecondAdapter secondAdapter) {
            super(view);
            mDate = (TextView) view.findViewById(R.id.dateView);
            mSubject1 = (TextView) view.findViewById(R.id.subjectPhysics);
            mSubject2 = (TextView) view.findViewById(R.id.subjectChemistry);
            mSubject3 = (TextView) view.findViewById(R.id.subjectMaths);
            mTeacher1 = (TextView) view.findViewById(R.id.teacherPhysics);
            mTeacher2 = (TextView) view.findViewById(R.id.teacherChemistry);
            mTeacher3 = (TextView) view.findViewById(R.id.teacherMaths);

        }

        public TextView getmDate() {
            return mDate;
        }

        public TextView getmSubject1() {
            return mSubject1;
        }

        public TextView getmSubject2() {
            return mSubject2;
        }

        public TextView getmSubject3() {
            return mSubject3;
        }

        public TextView getmTeacher1() {
            return mTeacher1;
        }

        public TextView getmTeacher2() {
            return mTeacher2;
        }

        public TextView getmTeacher3() {
            return mTeacher3;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item_day_schedule,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        mCursor.moveToPosition(position);
        holder.getmDate().setText(Utility.getProperDateFromLong(mCursor.getLong(Utility.COL_CLASS_INFO_DATE)));
        holder.getmSubject1().setText(mCursor.getString(Utility.COL_CLASS_INFO_SUBJECT_1));
        holder.getmTeacher1().setText(mCursor.getString(Utility.COL_CLASS_INFO_TEACHER_1));
        holder.getmSubject2().setText(mCursor.getString(Utility.COL_CLASS_INFO_SUBJECT_2));
        holder.getmTeacher2().setText(mCursor.getString(Utility.COL_CLASS_INFO_TEACHER_2));
        holder.getmSubject3().setText(mCursor.getString(Utility.COL_CLASS_INFO_SUBJECT_3));
        holder.getmTeacher3().setText(mCursor.getString(Utility.COL_CLASS_INFO_TEACHER_3));

    }

    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
