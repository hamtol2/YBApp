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
public class FacebookFragment extends WebViewBase
{
    private static FacebookFragment instance;
    public static FacebookFragment getInstance()
    {
        if (instance == null)
            instance = new FacebookFragment();

        return instance;
    }

    private View view;
    private String facebookURL = "https://m.facebook.com/OfficialYB";

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
            setWebSettings("UTF-8", facebookURL);
        }

        return view;
    }
}
