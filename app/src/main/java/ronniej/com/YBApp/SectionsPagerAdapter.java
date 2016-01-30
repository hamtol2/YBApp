package ronniej.com.YBApp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private TabInfoClass[] tabInfoClasses;

    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void setTabInfoClasses(TabInfoClass[] tabInfoClasses)
    {
        if (this.tabInfoClasses == null)
        {
            this.tabInfoClasses = new TabInfoClass[tabInfoClasses.length];
        }

        this.tabInfoClasses = tabInfoClasses;
    }

    public TabInfoClass getTabInfo(int position)
    {
        if (position >= tabInfoClasses.length)
            return null;

        return this.tabInfoClasses[position];
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position >= tabInfoClasses.length)
            return null;

        return tabInfoClasses[position].fragmentInstance;
    }

    @Override
    public int getCount()
    {
        return tabInfoClasses.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}