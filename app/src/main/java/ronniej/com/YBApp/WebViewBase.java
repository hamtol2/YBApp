package ronniej.com.YBApp;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.InvocationTargetException;

public class WebViewBase extends Fragment
{
    protected WebView webView;

    protected void setWebSettings(String textEncoding, String url)
    {
        webView.setWebChromeClient(new CustomChromeWebView(getActivity()));
        webView.setWebViewClient(new CustomWebView());
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName(textEncoding);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);
        webSettings.getLoadWithOverviewMode();

        webView.loadUrl(url);
    }

    public WebView getWebView()
    {
        return webView;
    }

    @Override
    public void onPause()
    {
        super.onPause();

        if (webView != null)
            webView.onPause();

        //Log.d("seyun", "onPause, ");
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (webView != null)
            webView.onResume();

        //Log.d("seyun", "onResume");
    }
}