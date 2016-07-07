package com.example.root.classmanagementsystem;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        updateDatabase();
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        java.util.Date date =  calendar.getTime();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long timeInLong =  gregorianCalendar.getTimeInMillis();
        calendar.setTimeInMillis(timeInLong);
        long increasedTime = timeInLong + 604800000L;
        String date1 = simpleDateFormat.format(calendar.getTime());
        calendar.setTimeInMillis(increasedTime);
        String date2 = simpleDateFormat.format(calendar.getTime());

        String date3 = "" + calendar.get(Calendar.DAY_OF_WEEK) + "/" + calendar.get(Calendar.MONTH);

        System.out.println("Date 3 is " + date3);

        System.out.println("simple date 2 is " + date2);
        System.out.println("simple date format is " + date1);

        System.out.println("time in  long is  " + timeInLong);


        System.out.println("Date is " + date.toString());

        Utility.databaseFiller();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "Last Week");
        adapter.addFragment(new SecondFragment(), "This Week");
        adapter.addFragment(new ThirdFragment(), "Next Week");
        adapter.addFragment(new FourthFragment(),"Coming Week");
        viewPager.setAdapter(adapter);
    }


    public void updateDatabase() {
        new BulkInsertTask(this).execute();
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
