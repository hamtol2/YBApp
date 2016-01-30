package ronniej.com.YBApp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by RonnieJ on 16. 1. 4..
 */
public class CustomWebView extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url)
    {
        webView.loadUrl(url);
        return true;
    }
}