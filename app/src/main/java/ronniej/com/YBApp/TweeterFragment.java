package ronniej.com.YBApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class TweeterFragment extends WebViewBase
{
    private static TweeterFragment instance;
    public static TweeterFragment getInstance()
    {
        if (instance == null)
            instance = new TweeterFragment();

        return instance;
    }

    private View view;
    private String tweeterURL = "https://mobile.twitter.com/official_yb";

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
            setWebSettings("UTF-8", tweeterURL);
        }

        return view;
    }
}