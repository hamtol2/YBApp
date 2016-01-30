package ronniej.com.YBApp;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

public class TabInfoClass
{
    public Fragment fragmentInstance;
    public String tabName;
    public String indicatorName;
    public Drawable indicatorDrawable;
    public int contentViewId;

    public TabInfoClass(Fragment fragmentInstance, String tabName, String indicatorName, Drawable indicatorDrawable, int contentViewId)
    {
        this.fragmentInstance = fragmentInstance;
        this.tabName = tabName;
        this.indicatorName = indicatorName;
        this.indicatorDrawable = indicatorDrawable;
        this.contentViewId = contentViewId;
    }
}
