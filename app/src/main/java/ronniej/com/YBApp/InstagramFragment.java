package ronniej.com.YBApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by RonnieJ on 2016-01-29.
 */
public class InstagramFragment extends WebViewBase
{
    private static InstagramFragment instance;
    public static InstagramFragment getInstance()
    {
        if (instance == null)
            instance = new InstagramFragment();

        return instance;
    }

    private View view;
    private String instagramURL = "https://www.instagram.com/yb_official_insta/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);

        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_webview, container, false);
            instance = this;

            webView = (WebView)view.findViewById(R.id.webView);
            setWebSettings("UTF-8", instagramURL);
        }

        return view;
    }
}
