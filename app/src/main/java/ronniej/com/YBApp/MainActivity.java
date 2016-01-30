package ronniej.com.YBApp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.OnTabChangeListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements OnTabChangeListener, OnPageChangeListener
{
    private static MainActivity instance;
    public static MainActivity getInstance()
    {
        if (instance == null)
            instance = new MainActivity();

        return instance;
    }

    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;

    private TabHost tabHost;
    private TabSpec tabSpec;

    // unit id "test id".
    private String admobUnitId = "ca-app-pub-3940256099942544/6300978111";
    private AdView adView;
    private AdRequest adRequest;

    private int currentActiveTab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBannerAd();

        // Create the adapter that will return a fragment for each of the three
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        initPagerAdapter();

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.setOnTabChangedListener(this);
        initTabInfo();

        currentActiveTab = 0;
        tabHost.setCurrentTab(currentActiveTab);

        instance = this;
    }

    private void initPagerAdapter()
    {
        TabInfoClass[] tabInfoClasses = new TabInfoClass[]
        {
            new TabInfoClass(YoutubeFragment.getInstance(), "Youtube", "", getDrawableFromId(R.drawable.youtube), android.R.id.tabcontent),
            new TabInfoClass(FacebookFragment.getInstance(), "Facebook", "", getDrawableFromId(R.drawable.facebook), android.R.id.tabcontent),
            new TabInfoClass(InstagramFragment.getInstance(), "Instagram", "", getDrawableFromId(R.drawable.instagram), android.R.id.tabcontent),
            new TabInfoClass(TweeterFragment.getInstance(), "Tweeter", "", getDrawableFromId(R.drawable.tweeter), android.R.id.tabcontent),
            new TabInfoClass(MarketBoardFragment.getInstance(), "YBMarket", "", getDrawableFromId(R.drawable.market_icon), android.R.id.tabcontent),
        };

        sectionsPagerAdapter.setTabInfoClasses(tabInfoClasses);
    }

    private void initTabInfo()
    {
        for (int ix = 0; ix < sectionsPagerAdapter.getCount(); ++ix)
        {
            TabInfoClass tabInfoClass = sectionsPagerAdapter.getTabInfo(ix);
            tabSpec = tabHost.newTabSpec(tabInfoClass.tabName);
            tabSpec.setIndicator(tabInfoClass.indicatorName, tabInfoClass.indicatorDrawable);
            tabSpec.setContent(tabInfoClass.contentViewId);
            tabHost.addTab(tabSpec);
        }
    }

    private Drawable getDrawableFromId(int resourceId)
    {
        Resources resources = getResources();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return resources.getDrawable(resourceId, getTheme());
        else
            return resources.getDrawable(resourceId);
    }

    private void setBannerAd()
    {
        adRequest = new AdRequest.Builder().build();

        adView = new AdView(this);
        adView.setAdUnitId(admobUnitId);
        adView.setAdSize(AdSize.BANNER);
        adView.loadAd(adRequest);

        LinearLayout adLayout = (LinearLayout)findViewById(R.id.adLayout);
        adLayout.addView(adView);
    }

    public void showQuitDialog()
    {
        if (!isFinishing())
        {
            Log.d("seyun", "showQuitDialog");

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (getFragmentManager().findFragmentByTag("quitPopup") != null)
            {
                ft.remove(getFragmentManager().findFragmentByTag("quitPopup"));
            }
            ft.addToBackStack(null);

            QuitPopupDialogFragment.getInstance().show(getFragmentManager(), "quitPopup");
        }
    }

    private void pauseWebview()
    {
        if (currentActiveTab == -1)
            return;

        TabInfoClass tabInfoClass = sectionsPagerAdapter.getTabInfo(currentActiveTab);
        if (tabInfoClass.fragmentInstance instanceof WebViewBase)
        {
            WebViewBase webViewBase = (WebViewBase)tabInfoClass.fragmentInstance;
            webViewBase.onPause();

            //Log.d("seyun", "pauseWebview, " + tabInfoClass.tabName);
        }
    }

    private void resumeWebview()
    {
        if (currentActiveTab == -1)
            return;

        TabInfoClass tabInfoClass = sectionsPagerAdapter.getTabInfo(currentActiveTab);
        if (tabInfoClass.fragmentInstance instanceof WebViewBase)
        {
            WebViewBase webViewBase = (WebViewBase)tabInfoClass.fragmentInstance;
            webViewBase.onResume();

            //Log.d("seyun", "resumeWebview, " + tabInfoClass.tabName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId)
    {
        int pageNumber = -1;

        // 페이지 번호 검색.
        for (int ix = 0; ix < sectionsPagerAdapter.getCount(); ++ix)
        {
            if (tabId.equals(sectionsPagerAdapter.getTabInfo(ix).tabName))
            {
                pageNumber = ix;
                break;
            }
        }

        // 페이지 번호가 제대로 검색이 된 경우에만 실행.
        if (pageNumber > -1)
        {
            //Log.d("seyun", "if (pageNumber != -1) " + String.valueOf(pageNumber));
            pauseWebview();

            currentActiveTab = pageNumber;
            viewPager.setCurrentItem(currentActiveTab);

            resumeWebview();
        }
    }

    @Override
    public void onPageScrolled(int pageNumber, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int pageNumber)
    {
        pauseWebview();

        currentActiveTab = pageNumber;
        tabHost.setCurrentTab(currentActiveTab);

        resumeWebview();
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            TabInfoClass tabInfoClass = sectionsPagerAdapter.getTabInfo(currentActiveTab);

            if (tabInfoClass.fragmentInstance instanceof WebViewBase)
            {
                WebView webView = ((WebViewBase)tabInfoClass.fragmentInstance).getWebView();
                if (webView.canGoBack())
                    webView.goBack();
                else
                    showQuitDialog();
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}