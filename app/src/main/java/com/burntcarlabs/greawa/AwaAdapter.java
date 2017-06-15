package com.burntcarlabs.greawa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Harshraj on 06-12-2016.
 */

public class AwaAdapter  extends FragmentPagerAdapter {


    private Context mContext;

    public AwaAdapter(FragmentManager fm) {
        super(fm);
    }

    public AwaAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
//
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new IssueFragment();
        } else if (position == 1){
            return new ArgumentFragment();
        } else  if (position == 2){
            return new BookmarkFragment();
        }else{
            return new ProgressFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Issue Essays";
        } else if (position == 1) {
            return "Argument Essays";
        } else if (position == 2) {
            return "Bookmarked Essays";
        } else {
            return "Progress";
        }
    }
}
