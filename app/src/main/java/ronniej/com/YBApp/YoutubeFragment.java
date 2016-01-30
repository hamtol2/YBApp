package ronniej.com.YBApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class YoutubeFragment extends WebViewBase
{
    private static YoutubeFragment instance;
    public static YoutubeFragment getInstance()
    {
        if (instance == null)
            instance = new YoutubeFragment();

        return instance;
    }

    private View view;
    private String youtubeURL = "http://m.youtube.com/#/user/ybrocks";

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
            setWebSettings("UTF-8", youtubeURL);
        }

        return view;
    }
}
