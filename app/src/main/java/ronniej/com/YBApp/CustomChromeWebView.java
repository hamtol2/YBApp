package ronniej.com.YBApp;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class CustomChromeWebView extends WebChromeClient
{
    private View customView;
    private Activity activity;

    private int originalOrientation;
    private FullScreenHolder holder;
    private CustomViewCallback customViewCallback;

    public CustomChromeWebView(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result)
    {
        result.confirm();
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback)
    {
        if (customView != null)
        {
            callback.onCustomViewHidden();
            return;
        }

        originalOrientation = activity.getRequestedOrientation();

        FrameLayout frameLayout = (FrameLayout)activity.getWindow().getDecorView();

        holder = new FullScreenHolder(activity);
        holder.addView(view, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.addView(holder, ViewGroup.LayoutParams.MATCH_PARENT);
        customView = view;
        customViewCallback = callback;
        activity.setRequestedOrientation(originalOrientation);
    }

    @Override
    public void onHideCustomView()
    {
        if (customView == null)
        {
            return;
        }

        FrameLayout frameLayout = (FrameLayout)activity.getWindow().getDecorView();
        frameLayout.removeView(holder);
        customView = null;
        customViewCallback.onCustomViewHidden();

        activity.setRequestedOrientation(originalOrientation);
    }

    private static class FullScreenHolder extends FrameLayout
    {
        public FullScreenHolder(Context context)
        {
            super(context);
            setBackgroundColor(context.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return true;
        }
    }
}